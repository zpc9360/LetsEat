package com.letseat.dto;

import lombok.Data;

/**
 * 购物车
 * @author zpc
 * @date 2018/10/8 18:11
 */
@Data
public class CartDTO {

     //商品Id
    private String productId;
    //商品数量
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
