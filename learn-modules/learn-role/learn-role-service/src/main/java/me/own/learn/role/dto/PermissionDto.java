package me.own.learn.role.dto;


/**
 * @author yexudong
 * @date 2019/5/29 16:19
 */
public class PermissionDto {
    private Long id;
    private String name;
    private String description;
    private Integer minLevel;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }
}
