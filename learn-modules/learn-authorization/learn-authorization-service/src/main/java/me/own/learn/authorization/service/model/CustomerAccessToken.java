package me.own.learn.authorization.service.model;

/**
 * @author yexudong
 * @date 2019/6/14 13:14
 */
public class CustomerAccessToken {
    private long customerId = 0;
    private String value;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
