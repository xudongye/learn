package me.own.learn.store.product.service;

import me.own.learn.store.product.constant.ProductConstant;

/**
 * @author yexudong
 * @date 2019/7/4 14:26
 */
public class ProductQueryCondition {
    private Long id;
    private String name;
    private String title;
    private String description;
    //类目id
    private Long categoryId;
    //商品状态，1-正常，2-下架
    private ProductConstant.Status status;
    private Long createTimeFrom;
    private Long createTimeTo;
    private Long modifyTimeFrom;
    private Long modifyTimeTo;
    private Long soldOutTimeFrom;
    private Long soldOutTimeTo;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getModifyTimeFrom() {
        return modifyTimeFrom;
    }

    public void setModifyTimeFrom(Long modifyTimeFrom) {
        this.modifyTimeFrom = modifyTimeFrom;
    }

    public Long getModifyTimeTo() {
        return modifyTimeTo;
    }

    public void setModifyTimeTo(Long modifyTimeTo) {
        this.modifyTimeTo = modifyTimeTo;
    }

    public Long getSoldOutTimeFrom() {
        return soldOutTimeFrom;
    }

    public void setSoldOutTimeFrom(Long soldOutTimeFrom) {
        this.soldOutTimeFrom = soldOutTimeFrom;
    }

    public Long getSoldOutTimeTo() {
        return soldOutTimeTo;
    }

    public void setSoldOutTimeTo(Long soldOutTimeTo) {
        this.soldOutTimeTo = soldOutTimeTo;
    }
}
