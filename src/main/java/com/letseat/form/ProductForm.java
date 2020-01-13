package com.letseat.form;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zpc
 * @date 2018/10/30 19:36
 */
@Data
public class ProductForm {
    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 类目编号. */
    private Integer categoryType;

    private Date updateTime;

}
