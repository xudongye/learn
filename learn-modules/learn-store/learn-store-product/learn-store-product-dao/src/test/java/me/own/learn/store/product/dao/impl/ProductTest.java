package me.own.learn.store.product.dao.impl;


import me.own.learn.store.product.dao.ProductDao;
import me.own.learn.store.product.po.Product;
import me.own.learn.test.base.BaseTestConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

//@ContextConfiguration(locations = {
//        "file:../../../../learn-api/src/main/webapp/WEB-INF/mvc-dispatcher-servlet-test.xml"
//})
public class ProductTest extends BaseTestConfiguration {

    @Autowired
    private ProductDao productDao;

//    @Test
//    @Rollback(value = false)
    public void testCreateProduct() throws Exception {
        Product product = new Product();
        product.setCategoryId(1L);
        product.setDeleted(false);
        product.setDescription("测试数据");
        product.setCreateTime(new Date());
        product.setInventory(1);
        product.setName("测试商品");
        product.setPrice(1l);
        product.setStatus(1);
        productDao.create(product);
    }
}
