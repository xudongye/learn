package me.own.learn.elsearch.dao.impl;

import me.own.learn.elsearch.po.ProductDocument;
import me.own.learn.elsearch.repository.ProductRepository;
import me.own.learn.test.base.BaseTestConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//
//@ContextConfiguration(locations = {
//        "file:D:\\yc_learn_project\\learn\\learn-api\\src\\test\\resources\\mvc-dispatcher-servlet-test.xml"
//})
public class ProductStoreTest extends BaseTestConfiguration {


    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCreateProductResp() {
        ProductDocument productDocument = new ProductDocument();
        productDocument.setSkuNo("sku34213123");
        productDocument.setProductName("西部数据128G");
        productDocument.setProductId(3L);
        productDocument.setCategoryName("硬盘");
        productDocument.setBrandName("西部数据");
        productRepository.index(productDocument);
    }

    @Test
    public void deleteProductDoc() {
        productRepository.delete("sku34213123");
    }

}
