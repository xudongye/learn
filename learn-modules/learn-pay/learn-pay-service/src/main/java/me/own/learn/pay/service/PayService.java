package me.own.learn.pay.service;

import me.own.learn.pay.bo.PayBusinessBo;
import me.own.learn.pay.bo.WxPayBusinessBo;
import me.own.learn.pay.exception.BusinessAlreadyPaidException;

public interface PayService {

    /***
     * Create wx pay business request and return wx prepayId and code Url
     *
     * @param payBusinessBo
     *         -- businessIds A list of businessId, businessId is determined by business Type,
     *                    that could be orderId or crowdSupportFundingId
     *         -- businessType Business type of business id list
     *                     1 - represents order
     *                     2 - represents crowd
     *                     see more definition in BusinessType
     *         -- returnUrl The return url after pay completion
     *         -- payByScan If pay by scan qr code
     *         -- socketId  The waiting socket client for receiving payment message
     *
     * @return
     * @throws BusinessAlreadyPaidException
     */
    WxPayBusinessBo createWxPayUnifiedBusiness(PayBusinessBo payBusinessBo) throws BusinessAlreadyPaidException;

    /***
     * Wx pay completion notification handler
     * @param resultXML Response data including the payment information in xml data format
     * @return true if pay success, otherwise false
     */
    boolean handleWxPayNotify(String resultXML);
}
