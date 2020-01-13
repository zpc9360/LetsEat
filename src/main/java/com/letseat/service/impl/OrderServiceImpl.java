package com.letseat.service.impl;

import com.letseat.converter.OrderMaster2OrderDTO;
import com.letseat.dao.OrderDetailDao;
import com.letseat.dao.OrderMasterDao;
import com.letseat.dto.CartDTO;
import com.letseat.dto.OrderDTO;
import com.letseat.enums.OrderStatusEnum;
import com.letseat.enums.PayStatusEnum;
import com.letseat.enums.ResultEnum;
import com.letseat.exception.SellException;
import com.letseat.po.OrderDetail;
import com.letseat.po.OrderMaster;
import com.letseat.po.ProductInfo;
import com.letseat.service.OrderService;
import com.letseat.service.ProductService;
import com.letseat.service.WebSocket;
import com.letseat.utils.RandomKeyUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zpc
 * @date 2018/10/8 15:44
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailDao detailDao;
    @Autowired
    private OrderMasterDao masterDao;
    @Autowired
    private PushMessageServiceImpl pushMessageService;
    @Autowired
    private WebSocket webSocket;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        //生成订单号
        String orderId = RandomKeyUtil.uniqueKey();
        //总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //查询商品
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);//抛出商品不存在异常
            }
            //计算总价（划重点！！！！！！！！！！）
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity())) //先用单价乘数量，计算相同物品价格
                    .add(orderAmount);//再相加，计算订单总价
            //订单详请入库
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(RandomKeyUtil.uniqueKey());
            BeanUtils.copyProperties(productInfo, orderDetail);//属性拷贝
            detailDao.save(orderDetail);
        }
        //写入订单数据库（主表和子表）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);//数据拷贝
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getStatusCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getStatusCode());
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setCreateTime(new Date());
        masterDao.save(orderMaster);
        //减去库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        //订单消息发送（卖家端）
        webSocket.sendMessage("有新订单啦！"+ orderDTO.getOrderId());
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = masterDao.getOne(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);//如果订单主表为空，抛出订单不存在异常
        }
        List<OrderDetail> orderDetailList = detailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    @Transactional
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = masterDao.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTO.convert(orderMasterPage.getContent());

        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());

    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getStatusCode())) {
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getStatusCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = masterDao.save(orderMaster);
        masterDao.save(orderMaster);
        System.out.println("orderMaster is :" + orderMaster.getOrderStatus());
        if (updateResult == null) {
            log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        //如果已支付, 需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getStatusCode())) {
            // payService.refund(orderDTO);
            //TODO
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getStatusCode())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getStatusCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);


        OrderMaster updateResult = masterDao.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        pushMessageService.orderStatus(orderDTO);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
//        //判断订单状态
//        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getStatusCode())) {
//            log.error("【支付完成订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
//            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
//        }
//
//        //判断支付状态
//        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getStatCode())) {
//            log.error("【订单支付完成】 订单支付状态不正确，orderDTO={}", orderDTO);
//            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
//        }

        //修改支付状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(PayStatusEnum.SUCCESS.getStatusCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);

        OrderMaster updateResult = masterDao.save(orderMaster);
        if (updateResult == null) {
            log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        pushMessageService.orderStatus(orderDTO);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = masterDao.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTO.convert(orderMasterPage.getContent());
        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }
}
