package com.letseat.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.letseat.dto.OrderDTO;
import com.letseat.enums.ResultEnum;
import com.letseat.exception.SellException;
import com.letseat.form.OrderForm;
import com.letseat.po.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * orderForm类转orderDTO类
 * @author zpc
 * @date 2018/10/17 15:39
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        Gson gson = new Gson();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());//把json数据转成List
        } catch (Exception e) {
            log.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
       // System.out.println(orderDetailList.toString()+"+++++++++++++++++++++++++");
        return orderDTO;

    }
}
