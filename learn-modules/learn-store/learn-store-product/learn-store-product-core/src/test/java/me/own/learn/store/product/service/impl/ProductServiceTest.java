package me.own.learn.store.product.service.impl;

import me.own.commons.base.model.IdSkeletonLong;
import me.own.learn.store.product.constant.ProductConstant;
import me.own.learn.store.product.dto.ProductDto;
import me.own.learn.store.product.service.ProductService;
import me.own.learn.store.product.vo.ProductVo;
import me.own.learn.test.base.BaseTestConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;


public class ProductServiceTest extends BaseTestConfiguration {

    @Autowired
    private ProductService productService;


    @Test
    public void getProductById() {
        ProductVo productVo = productService.getById(8L);
        System.out.println(productVo.toString());
    }

    @Test
    @Rollback(value = false)
    public void createProduct() {
        ProductDto productDto = new ProductDto();
        IdSkeletonLong c = new IdSkeletonLong();
        c.setId(1L);
        productDto.setCategory(c);
        productDto.setDescription("好1吃你就多吃点");
        productDto.setName("好吃点2");
        productDto.setTitle("意大利进口");
//        ProductVo productVo = productService.create(productDto);
//        System.out.println(productVo.toString());
    }
}
