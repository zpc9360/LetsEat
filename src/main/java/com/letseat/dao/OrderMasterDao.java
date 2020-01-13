package com.letseat.dao;

import com.letseat.po.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 订单主表
 * @author zpc
 * @date 2018/9/30 23:01
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {


    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);
}
