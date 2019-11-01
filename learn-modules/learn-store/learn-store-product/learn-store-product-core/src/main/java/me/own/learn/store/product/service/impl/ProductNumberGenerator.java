package me.own.learn.store.product.service.impl;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public class ProductNumberGenerator {

    public static String generate() {
        String prefix_tag = "SKY";
        String currentDateTimeText = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        String randomNumber = (Math.random() + "").replace(".", "").substring(0, 6);
        return prefix_tag + currentDateTimeText + randomNumber;
    }
}
