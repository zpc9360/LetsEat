package com.letseat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情
 *
 * @author zpc
 * @date 2018/9/29 17:50
 */
@Data
public class ProductInfoVO {
    @JsonProperty("id")//商品编号UUID
    private String productId;

    @JsonProperty("name")//商品名
    private String productName;

    @JsonProperty("price")//商品价格
    private BigDecimal productPrice;

    @JsonProperty("description")//商品描述
    private String productDescription;

    @JsonProperty("icon")//商品图片
    private String productIcon;


}
