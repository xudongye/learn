package me.own.learn.elsearch.service.impl;

import me.own.learn.elsearch.bo.ProductDocBo;
import me.own.learn.elsearch.service.ElsearchService;
import me.own.learn.test.base.BaseTestConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class ElsearchServiceTest extends BaseTestConfiguration {

    @Autowired
    private ElsearchService elsearchService;

    @Test
    public void testSearchByKeyword() {
        Page<ProductDocBo> productDocBos = elsearchService.getProductByName("手", 0, 10);
        System.out.println(productDocBos.getContent().size());
    }

    @Test
    public void testSave() {
        List<ProductDocBo> productDocBos = new ArrayList<>();
        ProductDocBo productDocBo1 = new ProductDocBo();
        productDocBo1.setBrandName("华为");
        productDocBo1.setSkuNo("sku13123123");
        productDocBo1.setCategoryName("手机数码");
        productDocBo1.setProductId(1L);
        productDocBo1.setProductName("手机");
        productDocBos.add(productDocBo1);

        ProductDocBo productDocBo2 = new ProductDocBo();
        productDocBo2.setSkuNo("sku7843535");
        productDocBo2.setBrandName("小米");
        productDocBo2.setCategoryName("手机数码");
        productDocBo2.setProductId(1L);
        productDocBo2.setProductName("手机");
        productDocBos.add(productDocBo2);

        ProductDocBo productDocBo3 = new ProductDocBo();
        productDocBo3.setSkuNo("sku3456456");
        productDocBo3.setBrandName("魅族");
        productDocBo3.setCategoryName("手机数码");
        productDocBo3.setProductId(1L);
        productDocBo3.setProductName("手机");
        productDocBos.add(productDocBo3);

        ProductDocBo productDocBo4 = new ProductDocBo();
        productDocBo4.setSkuNo("sku9077576");
        productDocBo4.setBrandName("华为");
        productDocBo4.setCategoryName("手机数码");
        productDocBo4.setProductId(4L);
        productDocBo4.setProductName("耳机");
        productDocBos.add(productDocBo4);


        ProductDocBo productDocBo5 = new ProductDocBo();
        productDocBo5.setSkuNo("sku1465679889");
        productDocBo5.setBrandName("魅族");
        productDocBo5.setCategoryName("手机数码");
        productDocBo5.setProductId(4L);
        productDocBo5.setProductName("耳机");
        productDocBos.add(productDocBo5);

        ProductDocBo productDocBo6 = new ProductDocBo();
        productDocBo6.setSkuNo("sku8564534345");
        productDocBo6.setBrandName("小米");
        productDocBo6.setCategoryName("手机数码");
        productDocBo6.setProductId(4L);
        productDocBo6.setProductName("耳机");
        productDocBos.add(productDocBo6);

        elsearchService.saveAll(productDocBos);
    }

}
