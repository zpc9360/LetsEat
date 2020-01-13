package com.letseat.enums;

import lombok.Getter;

/**
 * @author zpc
 * @date 2018/10/8 15:58
 */
@Getter
public enum ResultEnum {

    SUCCESS(0,"成功"),

    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_STOCK_ERROR(11, "商品库存错误"),

    ORDER_NOT_EXIST(12, "订单不存在"),

    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),

    ORDER_STATUS_ERROR(14, "订单状态错误"),

    ORDER_UPDATE_FAIL(15, "订单更新失败"),

    ORDER_DETAIL_EMPTY(16, "订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),

    CART_EMPTY(18,"购物车为空"),

    ORDER_OWNER_ERROR(19,"此订单不属于当前用户"),

    ORDER_CANCEL_SUCCESS(20,"订单取消成功"),

    ORDER_FINISH_SUCCESS(21,"完结订单成功"),

    PRODUCT_STATUS_ERROR(22,"商品状态错误"),

    WECHAT_MP_ERROR(23,"公众号异常"),

    ;

    private Integer statusCode;

    private String message;


    ResultEnum(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }


}
