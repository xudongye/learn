package me.own.learn.authorization.service.model;

import java.io.Serializable;

/**
 * @author yexudong
 * @date 2019/5/30 10:39
 */
public class AdminAccessToken implements Serializable {
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
