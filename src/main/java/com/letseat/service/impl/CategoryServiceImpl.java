package com.letseat.service.impl;

import com.letseat.dao.ProductCategoryDao;
import com.letseat.po.ProductCategory;
import com.letseat.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类
 * @author zpc
 * @date 2018/9/26 19:13
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryDao dao;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return dao.getOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return dao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return dao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return dao.save(productCategory);
    }
}
