package com.letseat.enums;


import lombok.Getter;

/**
 * @author zpc
 * @date 2018/9/30 22:34
 */
@Getter
public enum PayStatusEnum implements StatusCodeEnum{

    WAIT(0, "未支付"),
    SUCCESS(1, "已支付");

    private Integer statusCode;

    private String message;

    PayStatusEnum(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }


}
