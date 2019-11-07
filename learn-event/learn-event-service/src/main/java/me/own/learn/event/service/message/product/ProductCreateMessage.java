package me.own.learn.event.service.message.product;

import me.own.learn.event.service.MessageCarriable;

public class ProductCreateMessage implements MessageCarriable {

    private String skuNo;
    private String categoryName;
    private String productName;
    private Long productId;
    private String brandName;

    public ProductCreateMessage() {
    }

    public ProductCreateMessage(String skuNo, String categoryName, String productName, Long productId, String brandName) {
        this.skuNo = skuNo;
        this.categoryName = categoryName;
        this.productName = productName;
        this.productId = productId;
        this.brandName = brandName;
    }

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
