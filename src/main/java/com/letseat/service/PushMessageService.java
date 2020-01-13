package com.letseat.service;


import com.letseat.dto.OrderDTO;

/**
 * 推送消息
 * Created by zpc
 * 2018-11-30 22:08
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
