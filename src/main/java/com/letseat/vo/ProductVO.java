package com.letseat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品（类目）
 *
 * @author zpc
 * @date 2018/9/27 20:33
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList ;
}
