package me.own.learn.store.category.service;

/**
 * @author yexudong
 * @date 2019/6/12 15:48
 */
public class CategoryQueryCondition {
    private String name;
    //是否是父级目录
    private Boolean isParent;
    private Long parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
