package me.own.learn.sync.bo;

/**
 * @author yexudong
 * @date 2019/4/17 16:03
 */
public class RequestBo {

    private String partnerCode;
    private String version;

    private String requestType;
    private String signature;
    private Long timestamp;

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return '{' +
                "\"header\":{" +
                "\"partnerCode\":\"" + partnerCode + "\"" +
                ",\"version\":\"" + version + "\"" +
                ",\"requestType\":\"" + requestType + "\"" +
                ",\"signature\":\"" + signature + "\"" +
                ",\"timestamp\":" + timestamp + '}'
                + '}';
    }
}
