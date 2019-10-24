package me.own.learn.store.product.vo;

import java.util.Date;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/6/12 18:22
 */
public class ProductVo {
    private Long id;
    private String name;
    private String icon;
    private String description;
    //类目id
    private Long categoryId;
    private Date createTime;
    private Date modifyTime;
    private List<CarryVo> carries;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public List<CarryVo> getCarries() {
        return carries;
    }

    public void setCarries(List<CarryVo> carries) {
        this.carries = carries;
    }
}
