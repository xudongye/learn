package me.own.learn.pay.service.impl;

import com.tencent.WXPay;
import com.tencent.common.ConfigureManager;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.Signature;
import com.tencent.protocol.unified_order_protocol.UnifiedOrderReqData;
import me.own.commons.base.utils.enums.EnumUtil;
import me.own.learn.configuration.delegate.LearnConfigurationServiceDelegate;
import me.own.learn.configuration.service.LearnConfigurationService;
import me.own.learn.pay.bo.NotifyBusinessBo;
import me.own.learn.pay.bo.PayBusinessBo;
import me.own.learn.pay.bo.WxPayBusinessBo;
import me.own.learn.pay.dao.PayOrderDao;
import me.own.learn.pay.exception.BusinessAlreadyPaidException;
import me.own.learn.pay.exception.BusinessTypeNotSupportException;
import me.own.learn.pay.po.PayOrder;
import me.own.learn.pay.po.PayToInnerBusiness;
import me.own.learn.pay.service.PayBusinessDelegate;
import me.own.learn.pay.service.PayService;
import me.own.learn.pay.service.impl.business.BusinessType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PayServiceImpl implements PayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private PayOrderDao payOrderDao;

    @Autowired
    private Map<String, PayBusinessDelegate> payBusinessDelegateMap;

    private LearnConfigurationServiceDelegate delegate = LearnConfigurationServiceDelegate.getInstance();

    @Override
    @Transactional
    public WxPayBusinessBo createWxPayUnifiedBusiness(PayBusinessBo payBusinessBo) throws BusinessAlreadyPaidException {
        String tradeType = payBusinessBo.getTradeType();
        String appId = payBusinessBo.getAppId();
        String[] businessIds = payBusinessBo.getBusinessIds();
        int businessType = payBusinessBo.getBusinessType();
        WxPayBusinessBo result = new WxPayBusinessBo();
        PayBusinessDelegate payBusinessDelegate = getBusinessDelegate(businessType);
        if (payBusinessDelegate.isAlreadyPaid(payBusinessBo)) {
            throw new BusinessAlreadyPaidException();
        }

        String codeUrl = "";
        String prepayId = "";
        String mWebUrl = "";
        int totalFee = 0;
        // Check if a wxpay order exists
        PayOrder payOrder = payOrderDao.queryNotPaidByBusinessIds(businessIds, PayOrder.PayMethod.wxpay, businessType, tradeType);
        boolean needUpdate = false;

        if (payOrder != null) {
            /***
             * NATIVE 支付 对应codeUrl不为空
             * JSAPI  支付 对应prepayId不为空
             * MWEB   支付 对应mWebUrl不为空
             */
            boolean createdOnce = ifPayOrderValid(payOrder);
            Date lastModifiedTime = payOrder.getModifyTime() == null ? payOrder.getCreateTime() : payOrder.getModifyTime();
            boolean expired = hasPrepayIdExpired(tradeType, lastModifiedTime);
            if (!createdOnce || expired) {
                needUpdate = true;
            } else {
                codeUrl = payOrder.getCodeUrl();
                prepayId = payOrder.getPrepayId();
                totalFee = payOrder.getTotalFee();
            }
        }

        // Request wxpay for codeUrl and prepayId
        if (payOrder == null || needUpdate) {
            double realPay = payBusinessDelegate.getPaidPrice(payBusinessBo);
            totalFee = getTotalFee(realPay);
            String outTradeNo = OutTradeNoGenerator.generate(businessType);
            String attach = tradeType.equals("NATIVE") ? payBusinessBo.getCustomerId() + "" : "";
            String openid = (tradeType.equals("JSAPI") && StringUtils.isNotEmpty(payBusinessBo.getOpenId())) ? payBusinessBo.getOpenId() : "";
            UnifiedOrderReqData reqData = new UnifiedOrderReqData(appId, payBusinessDelegate.getSubject(), payBusinessDelegate.getBody(),
                    attach, outTradeNo, totalFee + "", "", "", delegate.getConfiguration().getPay().getWxpayCallback(),
                    tradeType, "", openid, payBusinessBo.getSpBillCreateIp(), payBusinessBo.getFingerPrint());
            UnifiedOrderBusinessRecorder recorder = new UnifiedOrderBusinessRecorder();
            try {
                WXPay.doUnifiedOrderBusiness(reqData, recorder);
                if (recorder.isSuccess()) {
                    codeUrl = recorder.getCodeUrl();
                    prepayId = recorder.getPrepayId();
                    mWebUrl = recorder.getMWebUrl();
                    // Create a new pay order record
                    if (payOrder == null) {
                        createPayOrder(payBusinessBo, outTradeNo, prepayId, totalFee, codeUrl, mWebUrl);
                    } else if (needUpdate) {
                        payOrder.setCodeUrl(codeUrl);
                        payOrder.setPrepayId(prepayId);
                        payOrder.setmWebUrl(mWebUrl);
                        payOrder.onModified();
                        payOrderDao.update(payOrder);
                        LOGGER.info("wxpay prepay updated, outTradeNo: {} businessIds: {}, codeUrl: {}, prepayId: {}, mWebUrl: {} ",
                                payOrder.getOutTradeNo(), payOrder.getPayToInnerBusinesses(), codeUrl, prepayId, mWebUrl);
                    }
                }
            } catch (Exception e) {
                LOGGER.error("do wxpay unified order business error:", e);
            }
        }

        // set prepay id
        result.setPrepayId(prepayId);

        // native pay, set code url
        if (tradeType.equals("NATIVE")) {
            result.setCodeUrl(codeUrl);
        }

        // wx h5 pay, set mweb_url
        if (tradeType.equals("MWEB")) {
            result.setCodeUrl(mWebUrl);
        }

        if (tradeType.equals("JSAPI")) {
            // First sign appid, timeStamp, nonceStr, package, signType
            result.setAppId(appId);
            result.setTimeStamp(new Date().getTime());
            result.setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
            result.setSignType("MD5");
            Map<String, Object> signMap = result.toMap();
            signMap.put("package", "prepay_id=" + prepayId);
            String paySign = Signature.getSign(ConfigureManager.get(appId).getKey(), signMap);
            result.setPaySign(paySign);
        }

        result.setBusinessIds(businessIds);
        result.setBusinessType(businessType);
        result.setTotalFee(totalFee);
        result.setPaid(false);
        LOGGER.debug("{} wxpay unified business return: {}", tradeType, result.toString());
        return result;
    }

    @Override
    public boolean handleWxPayNotify(String resultXML) {
        LOGGER.info("receiver wxpay notification xml:{}", resultXML);
        WxPayResultBusinessRecorder wxPayResultBusinessRecorder = new WxPayResultBusinessRecorder();
        try {
            WXPay.doHandlePayResultBusiness(resultXML, wxPayResultBusinessRecorder);
            if (wxPayResultBusinessRecorder.isSuccess()) {
                handleNotify(wxPayResultBusinessRecorder.getNotifyBusinessBo());
            }
        } catch (Exception e) {
            LOGGER.error("handle wxpay notification error:", e);
        }
        return wxPayResultBusinessRecorder.isSuccess();
    }


    private PayOrder createPayOrder(PayBusinessBo payBusinessBo, String outTradeNo, String prepayId, int totalFee, String codeUrl, String mWebUrl) {
        PayOrder payOrder = new PayOrder();
        PayOrder.PayMethod payMethod = payBusinessBo.getPayMethod().equals("alipay") ? PayOrder.PayMethod.alipay : PayOrder.PayMethod.wxpay;
        payOrder.setPayMethod(payMethod);
        payOrder.setTradeType(payBusinessBo.getTradeType());
        payOrder.setTotalFee(totalFee);
        payOrder.setOutTradeNo(outTradeNo);
        payOrder.setPrepayId(prepayId);
        payOrder.setCustomerId(payBusinessBo.getCustomerId());
        payOrder.setCodeUrl(codeUrl);
        payOrder.setmWebUrl(mWebUrl);
        payOrder.setPaid(false);
        List<PayToInnerBusiness> payToInnerBusinessList = new ArrayList<>();
        for (String businessId : payBusinessBo.getBusinessIds()) {
            PayToInnerBusiness payToInnerBusiness = new PayToInnerBusiness();
            payToInnerBusiness.setPayOrder(payOrder);
            payToInnerBusiness.setBusinessId(businessId);
            payToInnerBusiness.setBusinessType(payBusinessBo.getBusinessType());
            payToInnerBusinessList.add(payToInnerBusiness);
        }
        payOrder.setPayToInnerBusinesses(payToInnerBusinessList);
        payOrder.onCreated();
        payOrderDao.create(payOrder);
        LOGGER.info("create {} PayOrder.outTradeNo = {} for business: {} {} ", payMethod, outTradeNo, payBusinessBo.getBusinessType(), payBusinessBo.getBusinessIds());
        return payOrder;
    }

    /**
     * 处理已支付通知的通用逻辑，只有记录的次要参数略有不同
     * 1. 检验外部订单号前缀是否是支持的业务
     * 2. 检验是否已处理过
     * 3. 检验金额是否正确
     * 4. 更新PayOrder数据
     * 5. 将相关业务标记为已支付
     *
     * @param notifyBusinessBo
     */
    private void handleNotify(NotifyBusinessBo notifyBusinessBo) {
        LOGGER.info("handleNotify outTradeNo: ({})", notifyBusinessBo.getOutTradeNo());
        // check prefix
        int businessType = OutTradeNoGenerator.getBusinessTypeByOutTradePrefixTag(notifyBusinessBo.getOutTradeNo());
        PayOrder payOrder = payOrderDao.getByOutTradeNo(notifyBusinessBo.getOutTradeNo());
        if (payOrder.isPaid()) {
            // the order has been handled already.
            LOGGER.warn("handleNotify outTradeNo: ({}) is already paid.", notifyBusinessBo.getOutTradeNo());
            return;
        }
        // query businessIds from outTradeNo
        String[] businessIds = new String[payOrder.getPayToInnerBusinesses().size()];
        for (int i = 0; i < payOrder.getPayToInnerBusinesses().size(); i++) {
            businessIds[i] = payOrder.getPayToInnerBusinesses().get(i).getBusinessId();
        }
        // check money equals
        int needPayPrice = payOrder.getTotalFee();
        if (needPayPrice > notifyBusinessBo.getTotalFee()) {
            LOGGER.warn("handleNotify outTradeNo: ({}), pay price ({}) less than order ({}), quit marking order to paid.",
                    notifyBusinessBo.getOutTradeNo(), notifyBusinessBo.getTotalFee(), needPayPrice);
            // 2017/6/9 just warn, do not return, since alipay might give substract automatically
            // mostly between 0 ~ 2 RMB
            //return;
        }
        // update pay order data
        payOrder.setPaid(true);
        payOrder.setTradeType(notifyBusinessBo.getTradeType());
        payOrder.setTotalFee(notifyBusinessBo.getTotalFee());
        payOrder.setSettlement_total_fee(notifyBusinessBo.getSettlementTotalFee());
        payOrder.setTransactionId(notifyBusinessBo.getTransactionId());
        payOrder.setTimeEnd(notifyBusinessBo.getTimeEnd());
        payOrder.setBuyerId(notifyBusinessBo.getBuyerId());
        payOrder.setBuyerLogonId(notifyBusinessBo.getBuyerLogonId());
        payOrder.onModified();

        // delegate to business service
        PayBusinessDelegate payBusinessDelegate = getBusinessDelegate(businessType);
        PayBusinessBo payBusinessBo = new PayBusinessBo()
                .setBusinessIds(businessIds)
                .setPayMethod(payOrder.getPayMethod().toString())
                .setCustomerId(payOrder.getCustomerId());
        payBusinessDelegate.handlePayResult(payBusinessBo);
    }

    private boolean ifPayOrderValid(PayOrder payOrder) {
        String tradeType = payOrder.getTradeType();
        return (tradeType.equals("NATIVE") && StringUtils.isNotEmpty(payOrder.getCodeUrl()))
                || (tradeType.equals("JSAPI") && StringUtils.isNotEmpty(payOrder.getPrepayId())
                || tradeType.equals("MWEB") && StringUtils.isNotEmpty(payOrder.getmWebUrl()));
    }


    private boolean hasPrepayIdExpired(String tradeType, Date lastModifiedTime) {
        Calendar c1 = Calendar.getInstance();// 获取当前时间
        Calendar c2 = Calendar.getInstance();
        c2.setTime(lastModifiedTime);
        long currentTime = c1.getTimeInMillis();
        long createTime = c2.getTimeInMillis();
        long difference = currentTime - createTime;
        //long surviveTime = difference / (3600 * 1000);
        // 微信h5支付有效期为5分钟
        if (tradeType.equals("MWEB")) {
            return difference >= 5 * 60 * 1000;
        } else {
            // 二维码有效期为2小时
            return difference >= 2 * 3600 * 1000;
        }
    }

    private PayBusinessDelegate getBusinessDelegate(int businessType) {
        String serviceName = EnumUtil.getEnumObjectName(businessType, BusinessType.class) + "PayBusinessDelegateImpl";
        if (payBusinessDelegateMap.containsKey(serviceName)) {
            return payBusinessDelegateMap.get(serviceName);
        }

        throw new BusinessTypeNotSupportException();
    }

    private int getTotalFee(double realPay) {
        return (int) (realPay * 100);
    }
}
