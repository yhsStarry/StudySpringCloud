package com.yhs.order.tmpl;

import lombok.Data;

@Data
public class CartTmpl {

    private String productId;

    private Integer productQuantity;

    public CartTmpl(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
