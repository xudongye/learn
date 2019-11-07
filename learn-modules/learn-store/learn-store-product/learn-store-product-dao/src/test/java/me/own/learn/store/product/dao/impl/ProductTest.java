package me.own.learn.store.product.dao.impl;


import me.own.learn.store.product.dao.ProductDao;
import me.own.learn.store.product.dao.ProductPropertyItemDao;
import me.own.learn.store.product.po.Product;
import me.own.learn.store.product.po.ProductPropertyItem;
import me.own.learn.test.base.BaseTestConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

import static org.junit.Assert.assertTrue;

//@ContextConfiguration(locations = {
//        "file:../../../learn-api/src/test/resources/mvc-dispatcher-servlet-test.xml"
//})
public class ProductTest extends BaseTestConfiguration {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductPropertyItemDao productPropertyItemDao;

    @Test
    @Rollback(value = false)
    public void testCreateProduct() throws Exception {
        Product product = new Product();
        product.setDeleted(false);
        product.setCreateTime(new Date());
        product.setName("测试商品");
//        productDao.create(product);
    }

    @Test
    public void getProductById() {
        Product product = productDao.get(1L);
        System.out.println(product.getName());
    }

    @Test
    public void getCacheProduct(){

    }
    @Test
    public void testGetSkuNoByProductId(){
        System.out.println(productPropertyItemDao.getSkuNoByProductId(21L));
    }

}
