package com.yhs.product.repository;

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
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryService;

    @Test
    public void queryByCategoryTypeIn() throws Exception{
        List<ProductCategory> list = productCategoryService.queryByCategoryTypeIn(Arrays.asList(11, 12));
        Assert.assertTrue(list.size()>0);
    }
}