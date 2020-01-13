package com.letseat.service;

import com.letseat.dto.OrderDTO;

/**
 * @author zpc
 * @date 2018/11/23 17:50
 */
public interface PayService {

    void create(OrderDTO orderDTO);

    void finish(OrderDTO orderDTO);
}
