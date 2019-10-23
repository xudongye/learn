package me.own.learn.pubaccount.handler;

import me.own.commons.wechat.pubaccount.message.MessageEntity;
import me.own.commons.wechat.pubaccount.message.MessageResultListener;
import me.own.commons.wechat.pubaccount.message.response.BaseResponse;
import me.own.commons.wechat.pubaccount.message.response.TextResponse;
import me.own.learn.configuration.service.LearnConfigurationService;
import me.own.learn.event.service.EventService;
import me.own.learn.event.service.message.customer.CustomerSubscribeMessage;
import me.own.learn.event.service.message.customer.CustomerUnSubscribeMessage;
import me.own.learn.pubaccount.handler.customEvent.CustomMenuOnClickHandler;
import me.own.learn.pubconfiguration.bo.PubAccountTextMessageBo;
import me.own.learn.pubconfiguration.service.PubConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yexudong
 * @date 2019/5/31 14:02
 */
@Component
public class MessageResultListenerImpl implements MessageResultListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageResultListenerImpl.class);

    @Autowired
    private PubConfigurationService pubConfigurationService;

    @Autowired
    private LearnConfigurationService configurationService;

    @Resource(name = "doraemonMenuOnClickHandler")
    private CustomMenuOnClickHandler customMenuOnClickHandler;

    @Autowired
    private EventService eventService;


    @Override
    public BaseResponse onSubscribe(String appId, String fromUserOpenId, String eventKey) {
        PubAccountTextMessageBo onSubscribe = pubConfigurationService.getTextMessageResolver(appId, PubConfigurationService.PubConfigurationEvent.ON_SUBSCRIBE);
        String message = getResponseTextMessage(fromUserOpenId, onSubscribe.getValue());
        LOGGER.debug("onSubscribe response message {} to customer openid {}", message, fromUserOpenId);
        //fixme
        eventService.enqueue(
                EventService.EventName.CustomerEvent.CUSTOMER_SUBSCRIBE,
                new CustomerSubscribeMessage(appId, fromUserOpenId, eventKey));
        return new TextResponse(message);
    }

    private String getResponseTextMessage(String fromUserOpenId, String message) {
        String domain = configurationService.getConfiguration().getDomain();
        return message.replace("{{OPENID}}", fromUserOpenId).replace("{{DOMAIN}}", domain);
    }

    @Override
    public void onUnSubscribe(String appId, String fromUserOpenId) {
        eventService.enqueue(
                EventService.EventName.CustomerEvent.CUSTOMER_UNSUBSCRIBE,
                new CustomerUnSubscribeMessage(appId, fromUserOpenId)
        );
    }

    @Override
    public BaseResponse onScan(String appId, String fromUserOpenId, String eventKey) {
        PubAccountTextMessageBo onSubscribe = pubConfigurationService.getTextMessageResolver(appId, PubConfigurationService.PubConfigurationEvent.ON_SUBSCRIBE);
        LOGGER.info("on scan event key {} appAccount {} from user {} ", eventKey, appId, fromUserOpenId);
        if (eventKey.startsWith("qrscene_")) {
            eventKey = eventKey.substring("qrscene_".length());
        }
        String message = getResponseTextMessage(fromUserOpenId, onSubscribe.getValue().replace("{{inviteId}}", eventKey));
        //fixme
        LOGGER.debug("onScan subscribe response message {} to customer openid {}", message, fromUserOpenId);
        return new TextResponse(message);
    }

    @Override
    public BaseResponse onClick(String appId, MessageEntity messageEntity) {
        String eventKey = messageEntity.getEventKey();
        if ("doraemon".equalsIgnoreCase(eventKey)) {
            return customMenuOnClickHandler.handle(appId, messageEntity);
        }
        LOGGER.error("unknown click event key: {}", eventKey);
        return null;
    }
}
