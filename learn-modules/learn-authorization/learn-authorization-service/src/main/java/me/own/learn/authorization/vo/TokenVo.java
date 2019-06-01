package me.own.learn.authorization.vo;

/**
 * @author yexudong
 * @date 2019/5/30 9:44
 */
public class TokenVo {
    private Long adminId;
    private String value;
    private long timestamp;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
