package com.yhs.product.service;

import com.yhs.product.dto.ProductCategory;

import java.util.List;

public interface IProductCategoryService {

    /**
     * 根据类目查询信息
     * @param categoryTypeList 类目
     * @return list
     */
    List<ProductCategory> selectCategoryTypeIn(List<Integer> categoryTypeList);
}
