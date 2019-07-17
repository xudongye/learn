package me.own.learn.menu.dto;



/**
 * @author:simonye
 * @date 22:28 2019/6/1
 **/
public class MenuDto {
    private Long id;
    private String name;
    private String icon;
    private String url;
    private String keyWord;
    private Long parentId;
    /**
     * 自动展开
     */
    private Boolean isExpend;
    private Long permissionId;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getIsExpend() {
        return isExpend;
    }

    public void setIsExpend(Boolean isExpend) {
        this.isExpend = isExpend;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
