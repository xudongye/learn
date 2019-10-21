package me.own.learn.pay.bo;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

public class WxPayBusinessBo {
    private String[] businessIds;

    private Integer businessType;

    private String prepayId;

    private String codeUrl;

    private Boolean isPaid;

    private Long timeStamp;

    private String nonceStr;

    private String signType;

    private String paySign;

    private String appId;

    private Integer totalFee;

    public String[] getBusinessIds() {
        return businessIds;
    }

    public void setBusinessIds(String[] businessIds) {
        this.businessIds = businessIds;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public Boolean isPaid() {
        return isPaid;
    }

    public void setPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Map<String,Object> toMap(){
        Map<String,Object> map = new TreeMap<>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    @Override
    public String toString(){
        Map<String,Object> map = this.toMap();
        StringBuffer sb = new StringBuffer();
        for(String key : map.keySet()){
            sb.append(key + "=");
            sb.append(map.get(key));
            sb.append(" ");
        }
        return sb.toString();
    }
}