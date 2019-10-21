package me.own.learn.pay.service.impl;

import me.own.learn.pay.exception.BusinessTypeNotSupportException;
import me.own.learn.pay.exception.InvalidOutTradeNoPrefixException;
import me.own.learn.pay.service.impl.business.BusinessType;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class OutTradeNoGenerator {
    public static final String order_prefix_tag = "LSO";

    public static final String crowd_prefix_tag = "CFS";

    public static String generate(int businessType){
        String prefix_tag = getPrefixTag(businessType);
        String currentDateTimeText = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        String randomNumber = (Math.random() + "").replace(".", "").substring(0, 6);
        return  prefix_tag + currentDateTimeText + randomNumber;
    }

    public static int getBusinessTypeByOutTradePrefixTag(String outTradeNo) throws InvalidOutTradeNoPrefixException {

        if(outTradeNo.startsWith(OutTradeNoGenerator.order_prefix_tag)){
            return BusinessType.Order.getCode();
        }

        if(outTradeNo.startsWith(OutTradeNoGenerator.crowd_prefix_tag)){
            return BusinessType.Crowd.getCode();
        }

        throw new InvalidOutTradeNoPrefixException();
    }

    private static String getPrefixTag(int businessType){
        switch (businessType){
            case 1:
                return order_prefix_tag;
            case 2:
                return crowd_prefix_tag;
            default:
                throw new BusinessTypeNotSupportException();
        }
    }
}
