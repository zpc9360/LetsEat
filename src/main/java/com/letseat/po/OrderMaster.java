package com.letseat.po;

import com.letseat.enums.OrderStatusEnum;
import com.letseat.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zpc
 * @date 2018/9/30 17:58
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster{
    //订单id
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getStatusCode();
    //支付状态,0为未支付
    private Integer payStatus= PayStatusEnum.WAIT.getStatusCode();
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;


}
