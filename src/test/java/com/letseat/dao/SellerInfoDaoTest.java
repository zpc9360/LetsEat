package com.letseat.dao;

import com.letseat.po.SellerInfo;
import com.letseat.utils.RandomKeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.KeyUtil;

import static org.junit.Assert.*;

/**
 * @author zpc
 * @date 2018/10/31 10:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao dao;
    @Test


    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(RandomKeyUtil.uniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("jason");
        sellerInfo.setOpenid("152100744");
        SellerInfo result  = dao.save(sellerInfo);

        Assert.assertNotNull(result);
    }


    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = new SellerInfo();
        SellerInfo result = dao.findByOpenid("152100744");
        Assert.assertEquals("152100744",result.getOpenid());
    }
}