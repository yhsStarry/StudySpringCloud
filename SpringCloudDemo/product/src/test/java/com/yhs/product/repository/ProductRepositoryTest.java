package com.yhs.product.repository;

import com.yhs.product.dto.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productService;

    @Test
    public void queryByProductStatus() throws Exception{
        List<Product> productList = productService.queryByProductStatus(0);
        Assert.assertTrue(productList.size()>0);
    }

    @Test
    public void findByProductIdIn() throws Exception{
        List<Product> productList = productService.findByProductIdIn(Arrays.asList("157875196366160022",
                "157875227953464068"));
        Assert.assertTrue(productList.size()>0);
    }
}