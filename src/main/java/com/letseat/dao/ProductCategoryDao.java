package com.letseat.dao;
/**
 * @author zpc
 * @date 2018/9/26 19:06
 */

import com.letseat.po.ProductCategory;
        import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer>{

        List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
