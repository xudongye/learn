package me.own.learn.sync.vo;


/**
 * @author yexudong
 * @date 2019/4/16 18:16
 */
public class SignatureVo {
    private String requestType;
    private String signature;
    private Long timestamp;

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
