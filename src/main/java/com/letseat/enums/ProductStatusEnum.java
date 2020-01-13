package com.letseat.enums;

import lombok.Getter;

/**
 * 商品状态枚举类
 *
 * @author zpc
 * @date 2018/9/27 14:54
 */
@Getter
public enum ProductStatusEnum implements StatusCodeEnum{

    UP(0, "在架"),
    Down(1, "下架");

    private Integer StatusCode;

    private String message;

    ProductStatusEnum(Integer StatusCode, String message) {
        this.StatusCode = StatusCode;
        this.message = message;
    }
}
