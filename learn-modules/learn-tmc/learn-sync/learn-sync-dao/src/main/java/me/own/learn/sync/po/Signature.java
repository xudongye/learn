package me.own.learn.sync.po;

import java.io.Serializable;

/**
 * @author yexudong
 * @date 2019/4/16 18:08
 */

public class Signature implements Serializable {

    private String requestType;

    private String signature;

    private Long timestamp;

    public Signature() {
    }

    public Signature(String requestType, String signature, Long timestamp) {
        this.requestType = requestType;
        this.signature = signature;
        this.timestamp = timestamp;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
