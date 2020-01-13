package com.letseat.controller;

import com.letseat.converter.OrderForm2OrderDTOConverter;
import com.letseat.dto.OrderDTO;
import com.letseat.enums.ResultEnum;
import com.letseat.exception.SellException;
import com.letseat.form.OrderForm;
import com.letseat.service.BuyerService;
import com.letseat.service.OrderService;
import com.letseat.utils.ResultVOUtil;
import com.letseat.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zpc
 * @date 2018/10/17 14:33
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getStatusCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>(16);
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    /**
     * 订单列表
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "5") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【 查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);

        //当前页面的List
        return ResultVOUtil.success(orderDTOPage.getContent());
    }


    /**
     * 订单详情
     */
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {

        //TODO 2018-10-18
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    /**
     * 取消订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    @PostMapping("/cancel")
    public ResultVO<OrderDTO> cancel(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {

        //TODO 2018-10-18
        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }

}
