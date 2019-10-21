package me.own.learn.pay.bo;

import java.util.Date;

public class NotifyBusinessBo {
    private String outTradeNo;
    private String tradeType;

    /**
     * 订单金额
     */
    private int totalFee;
    private int settlementTotalFee;

    /**
     * 微信支付订单号
     */
    private String transactionId;

    private Date timeEnd;

    private String buyerId;

    private String buyerLogonId;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public NotifyBusinessBo setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public NotifyBusinessBo setTotalFee(int totalFee) {
        this.totalFee = totalFee;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public NotifyBusinessBo setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public NotifyBusinessBo setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
        return this;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public NotifyBusinessBo setBuyerId(String buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public NotifyBusinessBo setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
        return this;
    }

    public String getTradeType() {
        return tradeType;
    }

    public NotifyBusinessBo setTradeType(String tradeType) {
        this.tradeType = tradeType;
        return this;
    }

    public int getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public NotifyBusinessBo setSettlementTotalFee(int settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
        return this;
    }
}
