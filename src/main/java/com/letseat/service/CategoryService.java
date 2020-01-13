package com.letseat.service;

import com.letseat.po.ProductCategory;

import java.util.List;

/**
 * @author zpc
 * @date 2018/9/26 19:08
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);


}
