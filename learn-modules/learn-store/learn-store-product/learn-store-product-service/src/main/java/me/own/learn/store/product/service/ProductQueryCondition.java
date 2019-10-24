package me.own.learn.store.product.service;

import me.own.learn.store.product.constant.ProductConstant;

/**
 * @author yexudong
 * @date 2019/7/4 14:26
 */
public class ProductQueryCondition {
    private Long id;
    private String name;
    //类目id
    private Long categoryId;
    //商品状态，1-正常，2-下架
    private ProductConstant.Status status;
    private Long createTimeFrom;
    private Long createTimeTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public ProductConstant.Status getStatus() {
        return status;
    }

    public void setStatus(ProductConstant.Status status) {
        this.status = status;
    }

    public Long getCreateTimeFrom() {
        return createTimeFrom;
    }

    public void setCreateTimeFrom(Long createTimeFrom) {
        this.createTimeFrom = createTimeFrom;
    }

    public Long getCreateTimeTo() {
        return createTimeTo;
    }

    public void setCreateTimeTo(Long createTimeTo) {
        this.createTimeTo = createTimeTo;
    }
}
