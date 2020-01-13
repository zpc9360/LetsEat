package com.letseat.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author zpc
 * @date 2018/10/31 10:38
 */
@Data
@Entity
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
