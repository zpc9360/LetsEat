package com.letseat.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letseat.enums.OrderStatusEnum;
import com.letseat.enums.PayStatusEnum;
import com.letseat.po.OrderDetail;
import com.letseat.utils.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单详情的DTO类（用来替代订单类，传递数据）
 *
 * @author zpc
 * @date 2018/10/8 15:32
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)若属性为空，则不返回，已在yml文件进行了全局配置
public class OrderDTO {
    //订单id
    private String orderId;
    //卖家名字
    private String buyerName;
    //买家电话
    private String buyerPhone;
    //买家电话
    private String buyerAddress;
    //买家微信openId
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    //订单状态,0为新订单
    private Integer orderStatus;
    //支付状态,0为未支付
    private Integer payStatus;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //订单详情（一个OrderMaster对应多个OrderDetail）
    List<OrderDetail> orderDetailList;

    //获取订单状态枚举
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByStatusCode(orderStatus, OrderStatusEnum.class);
    }

    //获取支付状态枚举
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByStatusCode(payStatus, PayStatusEnum.class);
    }

}
