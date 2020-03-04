package me.own.learn.mall.product.vo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/4 14:07
 */
public class ProductCategoryWithChildrenItemVo extends ProductCategoryVo {
    private List<ProductCategoryVo> children;

    public List<ProductCategoryVo> getChildren() {
        return children;
    }

    public void setChildren(List<ProductCategoryVo> children) {
        this.children = children;
    }
}
