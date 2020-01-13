package com.letseat.service.impl;

import com.letseat.dao.ProductInfoDao;
import com.letseat.dto.CartDTO;
import com.letseat.enums.ProductStatusEnum;
import com.letseat.enums.ResultEnum;
import com.letseat.exception.SellException;
import com.letseat.po.ProductInfo;
import com.letseat.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @author zpc
 * @date 2018/9/27 14:50
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoDao dao;

    @Override
    public ProductInfo findOne(String productId) {
        return dao.getOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return dao.findByProductStatus(ProductStatusEnum.UP.getStatusCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return dao.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = dao.getOne(cartDTO.getProductId());

            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();

            productInfo.setProductStock(result);
            // System.out.println("+++++" + productInfo.toString() + "===");
            dao.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = dao.getOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            dao.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = dao.getOne(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新商品状态
        productInfo.setProductStatus(ProductStatusEnum.UP.getStatusCode());
        return dao.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = dao.getOne(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.Down) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新商品状态
        productInfo.setProductStatus(ProductStatusEnum.Down.getStatusCode());
        return dao.save(productInfo);
    }
}
