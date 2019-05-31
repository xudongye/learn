package me.own.learn.pubaccount.handler.customEvent;

import me.own.commons.wechat.pubaccount.message.MessageEntity;
import me.own.commons.wechat.pubaccount.message.response.BaseResponse;

/**
 * @author yexudong
 * @date 2019/5/31 14:01
 */
public interface CustomMenuOnClickHandler {
    BaseResponse handle(String appId, MessageEntity messageEntity) ;
}
