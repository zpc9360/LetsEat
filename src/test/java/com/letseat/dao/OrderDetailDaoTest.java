package com.letseat.dao;

import com.letseat.po.OrderDetail;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zpc
 * @date 2018/10/8 14:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao dao;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("002");
        orderDetail.setOrderId("222");
        orderDetail.setProductId("0002");
        orderDetail.setProductName("香瓜");
        orderDetail.setProductIcon("XXXX.jpg");
        orderDetail.setProductPrice(new BigDecimal(2.7));
        orderDetail.setProductQuantity(12);
        OrderDetail result = dao.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetails = dao.findByOrderId("1111");
        Assert.assertNotEquals(0,orderDetails.size());
    }
}