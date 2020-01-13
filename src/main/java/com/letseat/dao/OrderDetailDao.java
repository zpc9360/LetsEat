package com.letseat.dao;

import com.letseat.po.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单详情表
 * @author zpc
 * @date 2018/9/30 23:08
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrderId(String orderId);

}
