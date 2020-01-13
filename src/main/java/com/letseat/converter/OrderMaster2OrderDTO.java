package com.letseat.converter;

import com.letseat.dto.OrderDTO;
import com.letseat.po.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zpc
 * @date 2018/10/15 16:52
 */
public class OrderMaster2OrderDTO {

    //把OrderMaster转成OrderDTO
    private static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);//属性复制
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
       return orderMasterList.stream().map(OrderMaster2OrderDTO::convert
        ).collect(Collectors.toList());
    }
}
