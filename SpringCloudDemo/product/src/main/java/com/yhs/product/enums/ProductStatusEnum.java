package com.yhs.product.enums;

import lombok.Getter;

/**
 * 商品上下架状态
 */
@Getter
public enum ProductStatusEnum {
    ZERO(0, "在架"),
    ONE(1, "下架");

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
