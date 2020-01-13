package com.letseat.service.impl;

import com.letseat.dto.OrderDTO;
import com.letseat.enums.OrderStatusEnum;
import com.letseat.enums.PayStatusEnum;
import com.letseat.po.OrderDetail;
import com.letseat.po.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zpc
 * @date 2018/10/10 14:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class OrderServiceImplTest {


    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "1101110";

    private String ORDER_ID = "1539937608725501080";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName("张五星");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerPhone("412341234");
        orderDTO.setBuyerAddress("杭州市西湖区");
        orderDTO.setCreateTime(new Date());

        //购物车生成订单
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("4028b881661aff2d01661aff420f0000");
        o1.setProductQuantity(1);


        OrderDetail o2 = new OrderDetail();
        o2.setProductId("4028b8816619272a016619273b0b0000");//商品编号
        o2.setProductQuantity(1);//购买数量

        orderDetailList.add(o1);//添加订单项
        orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);


        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】 result={}", result);
        Assert.assertNotNull(result);

    }

    @Test
    @Transactional//不加事物的话会报session已经关闭的错误
    public void findOne() {
        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】 result{}", result);
        Assert.assertEquals(ORDER_ID, result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, request);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    @Transactional
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne("1539674566861566094");
        System.out.println("+++++++" + orderDTO.toString());
        OrderDTO result = orderService.cancel(orderDTO);
        System.out.println("________" + result.toString());
        Assert.assertEquals(OrderStatusEnum.CANCEL.getStatusCode(), result.getOrderStatus());
    }

    @Test
    @Transactional
    public void finish() {
        OrderDTO orderDTO = orderService.findOne("1539674566861566094");
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getStatusCode(), result.getOrderStatus());
    }

    @Test
    @Transactional
    public void paid() {
        OrderDTO orderDTO = orderService.findOne("1539674566861566094");
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getStatusCode(), result.getOrderStatus());
    }

    @Test
    @Transactional
    public void list() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }
}