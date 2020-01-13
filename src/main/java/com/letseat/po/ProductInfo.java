package com.letseat.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letseat.enums.ProductStatusEnum;
import com.letseat.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;



import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品类
 * @author zpc
 * @date 2018/9/26 19:59
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
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

    /** 状态, 0正常1下架. */
    private Integer productStatus
           = ProductStatusEnum.Down.getStatusCode();

    /** 类目编号. */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByStatusCode(productStatus, ProductStatusEnum.class);
    }
}
