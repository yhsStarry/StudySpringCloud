package com.yhs.product.service;

import com.yhs.product.dto.Product;
import com.yhs.product.tmpl.CartTmpl;

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

    /**
     * 扣库尊
     * @param cartTmpls
     */
    void decreaseSrock(List<CartTmpl> cartTmpls);
}
