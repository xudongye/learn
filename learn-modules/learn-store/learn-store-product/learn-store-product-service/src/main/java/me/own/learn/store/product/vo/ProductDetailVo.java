package me.own.learn.store.product.vo;

import java.util.Date;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/8/5 15:01
 */
public class ProductDetailVo {
    private Long id;
    private String spuNo;
    private String name;
    private String title;
    private String description;
    private Boolean deleted;
    private Date createTime;
    private Date modifyTime;
    private ProductCategoryVo productCategory;
    private List<PropertyItemValue> propertyItems;

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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public ProductCategoryVo getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategoryVo productCategory) {
        this.productCategory = productCategory;
    }

    public List<PropertyItemValue> getPropertyItems() {
        return propertyItems;
    }

    public void setPropertyItems(List<PropertyItemValue> propertyItems) {
        this.propertyItems = propertyItems;
    }
}
