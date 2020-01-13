package com.letseat.dao;

import com.letseat.po.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zpc
 * @date 2018/10/31 10:42
 */
public interface SellerInfoDao extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);
}
