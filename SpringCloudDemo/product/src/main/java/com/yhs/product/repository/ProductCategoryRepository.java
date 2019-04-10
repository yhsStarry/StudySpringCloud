package com.yhs.product.repository;

import com.yhs.product.dto.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品类目接口
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * 根据类目查询信息
     * @param categoryTypeList 类目
     * @return list
     */
    List<ProductCategory> queryByCategoryTypeIn(List<Integer> categoryTypeList);
}
