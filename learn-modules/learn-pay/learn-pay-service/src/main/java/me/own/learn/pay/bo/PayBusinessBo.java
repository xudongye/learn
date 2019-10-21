package me.own.learn.pay.bo;

public class PayBusinessBo {
    /**
     * The businessId list passed would be delegated to specified business service instance
     *
     * which could be orderId list , crowd support id list
     */
    private String[] businessIds;

    /***
     * Business type support could be:
     *
     * 1 - order
     * 2 - crowd
     * 3 - reward
     */
    private int businessType;

    /***
     * Support two pay method
     * -- alipay
     * -- wxpay
     */
    private String payMethod;

    /***
     * Alipay callback url while payment is completed
     *
     * it could be null if paymMethod is 'wxpay'
     */
    private String alipayReturnUrl;

    /***
     * WxPay and Alipay tradeType
     *   NATIVE -- wxpay, alipay 二维码支付
     *   JSAPI  -- wxpay 微信公众号支付
     *   MWEB   -- wxpay h5 支付
     *   APP    -- 移动app支付
     *   WAP    -- apipay  手机h5支付
     */
    private String tradeType;

    /***
     * JSAPI required open id
     */
    private String openId;

    /***
     * Wechat pubaccount appid
     */
    private String appId;

    /***
     * NATIVE required customer's id for socket notification
     */
    private Long customerId;

    /***
     * MWEB required customer's payment device ip address
     */
    private String spBillCreateIp;

    /***
     * MWEB required customer's device's borwser finger print
     */
    private String fingerPrint;

    public String[] getBusinessIds() {
        return businessIds;
    }

    public PayBusinessBo setBusinessIds(String[] businessIds) {
        this.businessIds = businessIds;
        return this;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public PayBusinessBo setPayMethod(String payMethod) {
        this.payMethod = payMethod;
        return this;
    }

    public String getAlipayReturnUrl() {
        return alipayReturnUrl;
    }

    public PayBusinessBo setAlipayReturnUrl(String alipayReturnUrl) {
        this.alipayReturnUrl = alipayReturnUrl;
        return this;
    }

    public int getBusinessType() {
        return businessType;
    }

    public PayBusinessBo setBusinessType(int businessType) {
        this.businessType = businessType;
        return this;
    }

    public String getTradeType() {
        return tradeType;
    }

    public PayBusinessBo setTradeType(String tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public PayBusinessBo setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public PayBusinessBo setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getSpBillCreateIp() {
        return spBillCreateIp;
    }

    public void setSpBillCreateIp(String spBillCreateIp) {
        this.spBillCreateIp = spBillCreateIp;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }
}
