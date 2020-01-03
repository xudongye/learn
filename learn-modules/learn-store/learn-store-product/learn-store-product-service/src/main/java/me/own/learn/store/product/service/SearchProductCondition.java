package me.own.learn.store.product.service;

import me.own.learn.store.product.constant.ProductConstant;

public class SearchProductCondition {

    private String keyword;

    private ProductConstant.PriceSort priceSort;

    private Long categoryId;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ProductConstant.PriceSort getPriceSort() {
        return priceSort;
    }

    public void setPriceSort(ProductConstant.PriceSort priceSort) {
        this.priceSort = priceSort;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
