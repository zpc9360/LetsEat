package com.letseat.enums;

import lombok.Getter;

/**
 * 订单状态枚举类
 *
 * @author zpc
 * @date 2018/9/30 22:21
 */
@Getter
public enum OrderStatusEnum implements StatusCodeEnum {
    NEW(0, "新订单"),
    FINISHED(1, "已完结"),
    CANCEL(2, "已取消"),
    ;

    private Integer statusCode;

    private String message;


    OrderStatusEnum(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
//    //遍历所有状态码，若状态存在就返回
//    public static OrderStatusEnum getOrderStatusEnum(Integer statusCode){
//        for(OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()){
//            if(orderStatusEnum.getStatusCode().equals(statusCode)){
//                return orderStatusEnum;
//            }
//        }
//       return null;
//    }


}
