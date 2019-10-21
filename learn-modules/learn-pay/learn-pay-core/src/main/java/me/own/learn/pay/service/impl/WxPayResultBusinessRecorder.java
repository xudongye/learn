package me.own.learn.pay.service.impl;

import com.tencent.business.HandlePayResultBusiness;
import com.tencent.protocol.pay_notification_protocol.PayNotificationResData;
import me.own.learn.pay.bo.NotifyBusinessBo;
import me.own.learn.pay.service.NotifyBusinessBoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class WxPayResultBusinessRecorder implements HandlePayResultBusiness.ResultListener, NotifyBusinessBoFactory {

    private static Logger logger = LoggerFactory.getLogger(WxPayResultBusinessRecorder.class);

    private boolean isSuccess = false;

    private String outTradeNo;

    /**
     * 交易类型：JSAPI、NATIVE、APP
     */
    private String tradeType;

    /**
     * 订单金额
     */
    private int totalFee;

    /**
     * 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    private int settlementTotalFee;

    /**
     * 微信支付订单号
     */
    private String transactionId;

    private Date timeEnd;

    private String buyerId;


    /***
     * 微信支付attach
     */
    private String attach;

    @Override
    public NotifyBusinessBo getNotifyBusinessBo() {
        return new NotifyBusinessBo()
                .setOutTradeNo(getOutTradeNo())
                .setTotalFee(getTotalFee())
                .setTradeType(getTradeType())
                .setSettlementTotalFee(getSettlementTotalFee())
                .setTransactionId(getTransactionId())
                .setTimeEnd(getTimeEnd())
                .setBuyerId("")
                .setBuyerLogonId("");
    }

    @Override
    public void onFailByReturnCodeError(PayNotificationResData payNotificationResData) {
        logger.error("【支付结果失败】请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问, {}", payNotificationResData);
    }

    @Override
    public void onFailByReturnCodeFail(PayNotificationResData payNotificationResData) {
        logger.error("【支付结果失败】API系统返回失败，请检测Post给API的数据是否规范合法, {}", payNotificationResData);
    }

    @Override
    public void onFailBySignInvalid(PayNotificationResData payNotificationResData) {
        logger.error("【支付结果失败】请求API返回的数据签名验证失败，有可能数据被篡改了, {}", payNotificationResData);
    }

    @Override
    public void onSuccess(PayNotificationResData payNotificationResData) {
        logger.info("wxpay【支付结果成功】, {}", payNotificationResData);
        this.isSuccess = true;
        this.outTradeNo = payNotificationResData.getOut_trade_no();
        this.tradeType = payNotificationResData.getTrade_type();
        this.totalFee = payNotificationResData.getTotal_fee();
        this.settlementTotalFee = payNotificationResData.getSettlement_total_fee();
        this.transactionId = payNotificationResData.getTransaction_id();
        this.buyerId = payNotificationResData.getOpenid();
        this.attach = payNotificationResData.getAttach();
        try {
            this.timeEnd = new SimpleDateFormat("yyyyMMddHHmmss").parse(payNotificationResData.getTime_end());
        } catch (ParseException e) {
            logger.warn("end time string parse error", e);
        }
    }

    @Override
    public void onUnknownFail(PayNotificationResData payNotificationResData) {
        logger.error("wxpay【支付结果失败】未知错误, {}", payNotificationResData);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public int getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
