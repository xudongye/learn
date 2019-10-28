package me.own.learn.store.category.vo;

import java.util.List;

public class CategoryListVo {

    private String name;

    private String picUrl;

    private List<CategoryListVo> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public List<CategoryListVo> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryListVo> children) {
        this.children = children;
    }
}
