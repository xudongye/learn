package me.own.learn.authorization.vo;

/**
 * @author yexudong
 * @date 2019/5/30 11:12
 */
public class AdminTokenVo {
    private long adminId;
    private String value;

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
