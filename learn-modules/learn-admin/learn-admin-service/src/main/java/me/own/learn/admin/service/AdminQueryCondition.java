package me.own.learn.admin.service;

/**
 * @author yexudong
 * @date 2019/5/28 14:21
 */
public class AdminQueryCondition {
    private String name;
    private String cellphone;
    private String email;
    private Long createFrom;
    private Long createTo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCreateFrom() {
        return createFrom;
    }

    public void setCreateFrom(Long createFrom) {
        this.createFrom = createFrom;
    }

    public Long getCreateTo() {
        return createTo;
    }

    public void setCreateTo(Long createTo) {
        this.createTo = createTo;
    }
}
