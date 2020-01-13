package com.letseat.controller;

import com.letseat.exception.SellException;
import com.letseat.form.ProductForm;
import com.letseat.po.ProductCategory;
import com.letseat.po.ProductInfo;
import com.letseat.service.CategoryService;
import com.letseat.service.ProductService;
import com.letseat.utils.RandomKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zpc
 * @date 2018/10/29 19:17
 */
@Controller
@Slf4j
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 商品页
     *
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,//当前页
                             @RequestParam(value = "size", defaultValue = "10") Integer size,//每页显示订单条数
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }


    /**
     * 商品上架
     *
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            log.error("【卖家端商品上架】出现异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/letseat/seller/pruduct/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/letseat/seller/product/list");
        return new ModelAndView("common/succes", map);
    }

    /**
     * 商品下架
     *
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            log.error("【卖家端商品下架】出现异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/letseat/seller/pruduct/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/letseat/seller/product/list");
        return new ModelAndView("common/succes", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }
        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
       // System.out.println("@@@@@@@@@@@@@@@@@@@"+categoryList);
        return new ModelAndView("product/index", map);
    }
    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/letseat/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空, 说明是新增
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId());
                form.setUpdateTime(new Date());
            } else {
                form.setProductId(RandomKeyUtil.uniqueKey());
                form.setUpdateTime(new Date());
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/letseat/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/letseat/seller/product/list");
        return new ModelAndView("common/succes", map);
    }
}
