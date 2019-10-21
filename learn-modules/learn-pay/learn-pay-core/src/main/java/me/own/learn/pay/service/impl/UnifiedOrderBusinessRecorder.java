package me.own.learn.pay.service.impl;

import com.tencent.business.UnifiedOrderBusiness;
import com.tencent.protocol.unified_order_protocol.UnifiedOrderResData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnifiedOrderBusinessRecorder implements UnifiedOrderBusiness.ResultListener {
    private static Logger logger = LoggerFactory.getLogger(UnifiedOrderBusinessRecorder.class);

    private String codeUrl ;
    private String prepayId;
    private String mWebUrl;

    private boolean isSuccess = false;

    @Override
    public void onFailByReturnCodeError(UnifiedOrderResData unifiedOrderResData) {
        logger.error("【统一下单失败】请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问, {}", unifiedOrderResData);
    }

    @Override
    public void onFailByReturnCodeFail(UnifiedOrderResData unifiedOrderResData) {
        logger.error("【统一下单失败】API系统返回失败，请检测Post给API的数据是否规范合法, {}", unifiedOrderResData);
    }

    @Override
    public void onFailBySignInvalid(UnifiedOrderResData unifiedOrderResData) {
        logger.error("【统一下单失败】请求API返回的数据签名验证失败，有可能数据被篡改了, {}", unifiedOrderResData);
    }

    @Override
    public void onSuccess(UnifiedOrderResData unifiedOrderResData) {
        this.isSuccess = true;
        this.codeUrl = unifiedOrderResData.getCode_url();
        this.prepayId = unifiedOrderResData.getPrepay_id();
        this.mWebUrl = unifiedOrderResData.getMweb_url();
    }

    @Override
    public void onUnknownFail(UnifiedOrderResData unifiedOrderResData) {
        logger.error("【统一下单失败】未知错误", unifiedOrderResData);
    }

    @Override
    public void onFailByDuplicateOrder(UnifiedOrderResData unifiedOrderResData) {
        logger.error("【统一下单失败】商户订单号重复，同一笔交易不能多次提交，请核实商户订单号是否重复提交, {}", unifiedOrderResData);
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public String getMWebUrl(){ return mWebUrl;}

    public boolean isSuccess() {
        return isSuccess;
    }
}
