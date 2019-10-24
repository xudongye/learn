package me.own.learn.store.category.dto;

/**
 * @author yexudong
 * @date 2019/6/12 15:15
 */
public class CategoryDto {
    private Long id;
    private String name;
    //排序序号
    private Integer sortOrder;

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

}
