package com.yhs.product.service;

import com.yhs.product.dto.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IProductServiceTest {

    @Autowired
    private IProductService productService;

    @Test
    public void queryByProductStatus() throws Exception{
        List<Product> productList = productService.queryByProductStatus(0);
        Assert.assertTrue(productList.size()>0);
    }

}