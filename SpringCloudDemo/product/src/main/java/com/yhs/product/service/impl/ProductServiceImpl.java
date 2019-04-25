package com.yhs.product.service.impl;

import com.yhs.product.dto.Product;
import com.yhs.product.enums.ProductStatusEnum;
import com.yhs.product.enums.ResultEnum;
import com.yhs.product.exception.ProductException;
import com.yhs.product.repository.ProductRepository;
import com.yhs.product.service.IProductService;
import com.yhs.product.tmpl.CartTmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional
    public void decreaseSrock(List<CartTmpl> cartTmpls) {
        if (CollectionUtils.isEmpty(cartTmpls)) {
            throw new RuntimeException("信息为空");
        }
        for (CartTmpl cartTmpl : cartTmpls) {
           Optional<Product> productOptional =  productRepository.findById(cartTmpl.getProductId());

           if (!productOptional.isPresent()) {
               throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
           }

           Product product = productOptional.get();
           Integer result = product.getProductStock() - cartTmpl.getProductQuantity();
           if (result < 0) {
               throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
           }
           product.setProductStock(result);
           productRepository.save(product);
        }
    }
}
