package me.own.learn.store.product.dto;

import me.own.learn.store.product.constant.ProductConstant;

/**
 * @author yexudong
 * @date 2019/6/12 18:22
 */
public class ProductDto {
    private Long id;
    private String name;
    //单位
    private String unit;
    //库存
    private Integer inventory;

    private String title;

    //价格，单位：分
    private Long price;
    private String description;
    //类目id
    private Long categoryId;
    //商品状态，1-正常，2-下架
    private ProductConstant.Status status;

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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
}
