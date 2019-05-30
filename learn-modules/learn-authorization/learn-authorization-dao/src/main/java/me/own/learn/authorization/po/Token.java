package me.own.learn.authorization.po;

import java.io.Serializable;

/**
 * @author yexudong
 * @date 2019/5/30 9:32
 */
public class Token implements Serializable {
    private Long adminId;
    private String value;
    private Long timestamp;

    public Token() {
    }

    public Token(Long adminId, String value, Long timestamp) {
        this.adminId = adminId;
        this.value = value;
        this.timestamp = timestamp;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
