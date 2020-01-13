package com.letseat.service.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.letseat.po.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 分类测试
 *
 * @author zpc
 * @date 2018/9/26 19:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {

        System.out.println(categoryService.findOne(1));

    }

    @Test
    public void findAll() {

        System.out.println(categoryService.findAll());

    }

    @Test
    public void findByCategoryTypeIn() {

        //List<ProductCategory> productCategoriyList = categoryService.findByCategoryTypeIn(Arrays.asList(1,2));
        //Assert.assertNotEquals(0,productCategoriyList.size());

    }

    @Test
    //添加test
    public void save() {
    }
}