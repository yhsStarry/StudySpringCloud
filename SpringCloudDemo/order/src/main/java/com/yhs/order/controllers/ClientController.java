package com.yhs.order.controllers;

import com.yhs.order.client.ProductClient;
import com.yhs.order.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;



    @GetMapping("/getProductList")
    public String getProductList(){
        List<Product> productList = productClient.selectForOrder(Arrays.asList("157875196366160022"));
        log.info("response={}", productList);
        return "ok";
    }

}
