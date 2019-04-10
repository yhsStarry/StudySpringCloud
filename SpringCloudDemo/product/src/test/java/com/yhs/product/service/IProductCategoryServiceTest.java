package com.yhs.product.service;

import com.yhs.product.dto.ProductCategory;
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
public class IProductCategoryServiceTest {

    @Autowired
    private IProductCategoryService productCategoryService;

    @Test
    public void selectCategoryTypeIn() throws Exception{
        List<ProductCategory> productList = productCategoryService.selectCategoryTypeIn(Arrays.asList(11, 22));
        Assert.assertTrue(productList.size()>0);
    }

}