package com.yhs.product.service;

import com.yhs.product.dto.Product;

import java.util.List;

public interface IProductService {

    /**
     * 查询所有在架商品列表
     * @param id 商品id
     * @return list
     */
    List<Product> selectAllProduct(String id);

    /**
     *
     * @param productIdList
     * @return
     */
    List<Product> findListForOrder(List<String> productIdList);
}
