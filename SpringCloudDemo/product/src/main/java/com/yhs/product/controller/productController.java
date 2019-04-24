package com.yhs.product.controller;

import com.yhs.product.dto.Product;
import com.yhs.product.dto.ProductCategory;
import com.yhs.product.service.IProductCategoryService;
import com.yhs.product.service.IProductService;
import com.yhs.product.utils.ResultVoUtil;
import com.yhs.product.vo.ProductCategoryVo;
import com.yhs.product.vo.ProductVo;
import com.yhs.product.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品Controller
 */
@RestController
@RequestMapping("/product")
public class productController {
    private final static String ZERO = "0";

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductCategoryService productCategoryService;

    /**
     *
     * 1. 查询所有在架的商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据
     * @return resultVo
     */
    @GetMapping("/list")
    public ResultVo<ProductVo> query(){
        //所有在架商品
        List<Product> productList = productService.selectAllProduct(ZERO);
        //取出所有在架商品的类目type
        List<Integer> categoryTypeList = productList.stream()
                .map(Product::getCategoryType).collect(Collectors.toList());
        //从数据库查询类目
        List<ProductCategory> productCategoryList =
                productCategoryService.selectCategoryTypeIn(categoryTypeList);

        //构造数据
        List<ProductCategoryVo> productCategoryVoList = new ArrayList<>();
        for (ProductCategory category: productCategoryList) {
            ProductCategoryVo categoryVo = new ProductCategoryVo();
            categoryVo.setCategoryName(category.getCategoryName());
            categoryVo.setCategoryType(category.getCategoryType());
            List<ProductVo> productVoList = new ArrayList<>();
            for (Product product: productList) {
                if (product.getCategoryType().equals(category.getCategoryType())) {
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(product, productVo);
                    productVoList.add(productVo);
                }
            }
            categoryVo.setProductVoList(productVoList);
            productCategoryVoList.add(categoryVo);
        }

        return ResultVoUtil.success(productCategoryVoList);
    }

    /**
     * 获取商品列表(给订单服务用)
     * @param productIdList
     * @return
     */
    @PostMapping("/selectForOrder")
    public List<Product> selectForOrder(@RequestBody List<String> productIdList){

        return productService.findListForOrder(productIdList);
    }

}
