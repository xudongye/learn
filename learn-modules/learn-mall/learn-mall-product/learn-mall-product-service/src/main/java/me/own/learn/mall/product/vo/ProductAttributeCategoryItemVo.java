package me.own.learn.mall.product.vo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/4 10:18
 */
public class ProductAttributeCategoryItemVo extends ProductAttributeCategoryVo {

    private List<ProductAttributeVo> productAttributeList;

    public List<ProductAttributeVo> getProductAttributeList() {
        return productAttributeList;
    }

    public void setProductAttributeList(List<ProductAttributeVo> productAttributeList) {
        this.productAttributeList = productAttributeList;
    }
}
