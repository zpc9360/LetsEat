package com.letseat.controller;

import com.letseat.exception.SellException;
import com.letseat.form.CategoryForm;
import com.letseat.po.ProductCategory;
import com.letseat.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 卖家分类管理
 *
 * @author zpc
 * @date 2018/11/2 10:15
 */
@RestController
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 卖家类目
     *
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    /**
     * 修改分类
     *
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null) {
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("category", productCategory);
        }
        return new ModelAndView("category/index", map);
    }

    /**
     * 添加分类
     *
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/letseat/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        ProductCategory productCategory = new ProductCategory();
        try {
            if (form.getCategoryId() != null) {
                productCategory = categoryService.findOne(form.getCategoryId());
            }
            form.setCreateTime(new Date());
            BeanUtils.copyProperties(form, productCategory);
            categoryService.save(productCategory);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/letseat/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/letseat/seller/category/list");
        return new ModelAndView("common/succes", map);
    }
}

