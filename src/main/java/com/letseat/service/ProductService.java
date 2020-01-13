package com.letseat.service;

import com.letseat.dto.CartDTO;
import com.letseat.po.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 *
 * @author zpc
 * @date 2018/9/27 14:43
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * 客户端查询
     */
    List<ProductInfo> findUpAll();

    /**
     * 管理端查询
     */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);
    /**
     * 加库存
     */
    void increaseStock(List<CartDTO> cartDTOList);


    /**
     * 减库存
     */
    void decreaseStock(List<CartDTO> cartDTOList);

    /**
     *
     * 商品上架
     */
    ProductInfo onSale(String productId);

    /**
     *
     *商品下架
     */
    ProductInfo offSale(String productId);
}
