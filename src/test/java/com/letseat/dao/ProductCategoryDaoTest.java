package com.letseat.dao;

import com.letseat.po.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

/**
 * @author zpc
 * @date 2018/9/26 11:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryDao dao;

    @Test
    @Transactional
    public void findOneTest(){
        ProductCategory productCategory = dao.getOne(1);
        System.out.println(productCategory.toString()+"***");
    }

    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("砂锅冒菜wppp1");
        productCategory.setCategoryType(21);
        productCategory.setCreateTime(new Date());
        dao.save(productCategory);
    }


}