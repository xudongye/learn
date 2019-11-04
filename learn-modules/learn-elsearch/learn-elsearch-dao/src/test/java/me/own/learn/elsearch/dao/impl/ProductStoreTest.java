package me.own.learn.elsearch.dao.impl;

import me.own.learn.elsearch.po.CategoryDocument;
import me.own.learn.elsearch.po.ProductDocument;
import me.own.learn.elsearch.repository.CategoryRepository;
import me.own.learn.elsearch.repository.ProductRepository;
import me.own.learn.test.base.BaseTestConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

//
//@ContextConfiguration(locations = {
//        "file:D:\\yc_learn_project\\learn\\learn-api\\src\\test\\resources\\mvc-dispatcher-servlet-test.xml"
//})
public class ProductStoreTest extends BaseTestConfiguration {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateProductResp() {
        ProductDocument productDocument = new ProductDocument();
        productDocument.setId(3L);
        productDocument.setProductNo("sku63457677886");
        productDocument.setName("苹果手机");
        productDocument.setTitle("官方授权直营");
        productDocument.setDescription("苹果手机（apple）(iphone)");
        productDocument.setCategoryName("手机电脑");
        productRepository.index(productDocument);
    }

    @Test
    public void deleteProductDoc() {
        productRepository.delete(1L);
    }

    @Test
    public void testCreateCategoryResp() {
        CategoryDocument categoryDocument = new CategoryDocument();
        categoryDocument.setId(2L);
        categoryDocument.setName("水果");
        categoryRepository.index(categoryDocument);
    }


}
