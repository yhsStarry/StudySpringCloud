package com.yhs.product.repository;

import com.yhs.product.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品服务接口
 */
public interface ProductRepository extends JpaRepository<Product, String> {
    /**
     * 根据商品状态查询
     * @param productStatus 商品状态
     * @return List集合
     */
    List<Product> queryByProductStatus(Integer productStatus);

    List<Product> findByProductIdIn(List<String> productList);
}
