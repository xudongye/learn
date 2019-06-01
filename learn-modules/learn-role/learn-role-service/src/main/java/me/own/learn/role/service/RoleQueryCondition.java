package me.own.learn.role.service;

/**
 * @author yexudong
 * @date 2019/5/28 15:35
 */
public class RoleQueryCondition {
    private String name;
    private String description;
    private Long level;

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

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}
