package com.letseat.service;

import com.letseat.dto.OrderDTO;

/**
 *
 * @author zpc
 * @date 2018/10/18 11:15
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid,String orderid);


}
