package com.yhs.product.service.impl;

import com.yhs.product.dto.ProductCategory;
import com.yhs.product.repository.ProductCategoryRepository;
import com.yhs.product.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> selectCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.queryByCategoryTypeIn(categoryTypeList);
    }
}
