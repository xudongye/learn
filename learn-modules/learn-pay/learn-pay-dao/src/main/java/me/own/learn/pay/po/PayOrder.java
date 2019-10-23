package me.own.learn.pay.po;

import me.own.commons.base.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class PayOrder extends BaseEntity implements Serializable {

    public enum PayMethod {
        alipay,
        wxpay
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 第三方支付的订单标识
     * 订单支付的编码 -- LSO2016030100001 (LSO+订单编号)
     * 众筹支付的编码 -- LCO
     */
    private String outTradeNo;
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    private String prepayId;
    /**
     * 用户下发通知标识
     */
    private long customerId;
    /**
     * 扫码地址, prepayUrl
     */
    private String codeUrl;
    /***
     * 微信h5浏览器支付跳转url
     */
    private String mWebUrl;
    @Column(name = "isPaid")
    private boolean paid;
    /**
     * 交易类型：JSAPI、NATIVE、APP、WAP、MWEB
     */
    private String tradeType;
    /**
     * 订单金额, 单位为分
     */
    private int totalFee;
    /**
     * 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    private int settlement_total_fee;

    /**
     * 微信支付订单号
     */
    private String transactionId;

    /**
     * 买家ID，为微信openid或者支付宝ID
     */
    private String buyerId;

    /**
     * 买家支付宝账号
     */
    private String buyerLogonId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeEnd;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PAY_BUSINESS_ID")
    private List<PayToInnerBusiness> payToInnerBusinesses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getmWebUrl() {
        return mWebUrl;
    }

    public void setmWebUrl(String mWebUrl) {
        this.mWebUrl = mWebUrl;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public int getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(int settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public List<PayToInnerBusiness> getPayToInnerBusinesses() {
        return payToInnerBusinesses;
    }

    public void setPayToInnerBusinesses(List<PayToInnerBusiness> payToInnerBusinesses) {
        this.payToInnerBusinesses = payToInnerBusinesses;
    }
}
