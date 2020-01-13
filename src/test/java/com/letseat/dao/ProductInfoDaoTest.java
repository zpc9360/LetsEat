package com.letseat.dao;

import com.letseat.po.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @author zpc
 * @date 2018/9/26 20:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao dao;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();

        productInfo.setProductName("rice");
        productInfo.setProductPrice(new BigDecimal(5.5));
        productInfo.setProductDescription("teast good");
        productInfo.setProductStatus(0);
        productInfo.setProductStock(4445);
        productInfo.setCategoryType(11);
        productInfo.setCreateTime(new Date());
        ProductInfo result=dao.save(productInfo);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfos = dao.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfos.size());
    }
}