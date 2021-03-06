package me.own.learn.store.product.vo;

import me.own.commons.base.model.IdSkeletonLong;
import me.own.commons.base.model.NameSkeletonString;

import java.util.Date;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/6/12 18:22
 */
public class ProductVo {
    private Long id;
    private String spuNo;
    private String name;
    private String description;
    private String brandName;
    //类目id
    private NameSkeletonString category;
    private Date createTime;
    private Date modifyTime;
    private List<PropertyItemValue> propertyItems;
    private Double originalPrice;
    private Double promotionPrice;
    private Long hitCount;
    private Long saleCount;
    private int status;

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

    public NameSkeletonString getCategory() {
        return category;
    }

    public void setCategory(NameSkeletonString category) {
        this.category = category;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<PropertyItemValue> getPropertyItems() {
        return propertyItems;
    }

    public void setPropertyItems(List<PropertyItemValue> propertyItems) {
        this.propertyItems = propertyItems;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
