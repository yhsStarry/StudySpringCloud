package com.yhs.product.service.impl;

import com.yhs.product.dto.Product;
import com.yhs.product.enums.ProductStatusEnum;
import com.yhs.product.repository.ProductRepository;
import com.yhs.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> selectAllProduct(String id) {
        return productRepository.queryByProductStatus(ProductStatusEnum.ZERO.getCode());
    }

    @Override
    public List<Product> findListForOrder(List<String> productIdList) {
        return productRepository.findByProductIdIn(productIdList);
    }
}
