package com.letseat.service.impl;

import com.letseat.enums.ProductStatusEnum;
import com.letseat.po.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zpc
 * @date 2018/9/27 15:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("e4793c43-758e-468a-9d9f-32f9a854f1a7");
        Assert.assertEquals("e4793c43-758e-468a-9d9f-32f9a854f1a7",productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfos = productService.findUpAll();
        Assert.assertNotEquals(0,productInfos.size());
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());

    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("4028b881667bb02501667bb03cf40000");
        productInfo.setProductName(" water rice");
        productInfo.setProductPrice(new BigDecimal(6.5));
        productInfo.setProductDescription("teast good");
        productInfo.setProductStatus(ProductStatusEnum.UP.getStatusCode());
        productInfo.setProductStock(20);
        productInfo.setCategoryType(2);
        productInfo.setCreateTime(new Date());
        ProductInfo res = productService.save(productInfo);
        Assert.assertNotNull(res);

    }
    @Test
    @Transactional
    public void onsale() {
        ProductInfo result = productService.onSale("4028b881661ad75801661ad76ec90000");
        Assert.assertEquals(ProductStatusEnum.UP,result.getProductStatusEnum());
    }
}