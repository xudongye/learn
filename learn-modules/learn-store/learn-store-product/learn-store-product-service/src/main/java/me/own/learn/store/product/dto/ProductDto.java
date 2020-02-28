package me.own.learn.store.product.dto;


import me.own.commons.base.model.IdSkeletonLong;
import me.own.learn.store.product.constant.ProductConstant;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/6/12 18:22
 */
public class ProductDto {
    private Long id;
    private String spuNo;
    private String name;
    private String icon;
    private String title;
    private String description;
    private String brandName;
    //类目id
    private IdSkeletonLong category;
    private Double originalPrice;
    private Double promotionPrice;
    private Long hitCount;
    private Long saleCount;
    private ProductConstant.SoldStatus soldStatus;
    private List<ProductImageDto> images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpuNo() {
        return spuNo;
    }

    public void setSpuNo(String spuNo) {
        this.spuNo = spuNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public IdSkeletonLong getCategory() {
        return category;
    }

    public void setCategory(IdSkeletonLong category) {
        this.category = category;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Long getHitCount() {
        return hitCount;
    }

    public void setHitCount(Long hitCount) {
        this.hitCount = hitCount;
    }

    public Long getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Long saleCount) {
        this.saleCount = saleCount;
    }

    public ProductConstant.SoldStatus getSoldStatus() {
        return soldStatus;
    }

    public void setSoldStatus(ProductConstant.SoldStatus soldStatus) {
        this.soldStatus = soldStatus;
    }

    public List<ProductImageDto> getImages() {
        return images;
    }

    public void setImages(List<ProductImageDto> images) {
        this.images = images;
    }
}
