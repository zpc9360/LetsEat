package com.letseat.po;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author zpc
 * @date 2018/9/30 22:54
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {
    @Id
    //详情ID
    private String detailId;
    //订单ID
    private String orderId;
    //商品ID
    private String productId;
    //商品名称

    private String productName;
    //单价
    private BigDecimal productPrice;
    //商品数量
    private Integer productQuantity;
    //商品图片
    private String productIcon;


}
