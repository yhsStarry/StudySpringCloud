package com.yhs.product.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryVo {
    /**
     * 类目名称
     */
    @JsonProperty("name")
    private String categoryName;

    /**
     * 商品类别
     */
    @JsonProperty("type")
    private Integer categoryType;

    /**
     * 商品详细信息
     */
    @JsonProperty("foods")
    private List<ProductVo> productVoList;

}
