package com.yhs.product.service;

import com.yhs.product.dto.Product;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;

import java.util.List;

/**
 * 商品服务接口
 */
public interface IProductService extends JpaAttributeConverter<Product, String> {
    /**
     * 根据商品状态查询
     * @param productStatus 商品状态
     * @return List集合
     */
    List<Product> queryByProductStatus(Integer productStatus);
}
