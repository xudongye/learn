package me.own.learn.store.product.vo;

import java.util.Date;

/**
 * @author yexudong
 * @date 2019/8/5 15:03
 */
public class ProductCategoryVo {
    private Long id;
    private String name;
    //排序序号
    private Integer sortOrder;
    private Boolean deleted;
    private Date createTime;
    private Date modifyTime;
    private ProductCategoryVo parent;

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

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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

    public ProductCategoryVo getParent() {
        return parent;
    }

    public void setParent(ProductCategoryVo parent) {
        this.parent = parent;
    }
}
