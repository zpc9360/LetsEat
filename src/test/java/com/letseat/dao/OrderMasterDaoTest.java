package com.letseat.dao;

import com.letseat.po.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zpc
 * @date 2018/9/30 23:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao dao;

    private final String OPENID = "152100744";

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("222");
        orderMaster.setBuyerAddress("内蒙古呼和浩特");
        orderMaster.setBuyerName("zpc222");
        orderMaster.setBuyerPhone("13500615010222");
        orderMaster.setOrderAmount(new BigDecimal(3.5));
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setCreateTime(new Date());
        OrderMaster result = dao.save(orderMaster);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(0, 1,Sort.unsorted());
        Page<OrderMaster> result =  dao.findByBuyerOpenid(OPENID,request);
        Assert.assertNotEquals(0,result.getTotalElements());
        //System.out.println(result.getTotalElements());
    }
}