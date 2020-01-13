package com.letseat.controller;

import com.letseat.dto.OrderDTO;
import com.letseat.enums.PayStatusEnum;
import com.letseat.enums.ResultEnum;
import com.letseat.exception.SellException;
import com.letseat.service.OrderService;
import com.letseat.service.PayService;
import com.letseat.service.PushMessageService;
import com.letseat.service.impl.PushMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author zpc
 * @date 2018/11/23 10:06
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;



    @GetMapping("/create")
    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl,
                       Map<String, Object> map) {

        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
            //2.发起支付
        } else {
            //TODO
        }

    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
       // orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getStatusCode());

        orderService.paid(orderDTO);

        map.put("url", "http://phewww.club");
        return new ModelAndView("pay/finish", map);

    }
}
