package me.own.learn.store.category.vo;

import java.util.Date;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/6/12 15:15
 */
public class CategoryVo {
    private Long id;
    private String name;
    //排序序号
    private Integer sortOrder;
    private Boolean deleted;
    private Date createTime;
    private Date modifyTime;
    private CategoryVo parent;
//    private List<CategoryVo> children;
    private String picUrl;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

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

    public CategoryVo getParent() {
        return parent;
    }

    public void setParent(CategoryVo parent) {
        this.parent = parent;
    }

//    public List<CategoryVo> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<CategoryVo> children) {
//        this.children = children;
//    }
}
