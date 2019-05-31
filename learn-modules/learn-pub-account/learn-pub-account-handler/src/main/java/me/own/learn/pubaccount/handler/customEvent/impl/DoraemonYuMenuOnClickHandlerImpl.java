package me.own.learn.pubaccount.handler.customEvent.impl;

import me.own.commons.base.utils.mapper.Mapper;
import me.own.commons.wechat.pubaccount.message.MessageEntity;
import me.own.commons.wechat.pubaccount.message.response.ArticlesResponse;
import me.own.commons.wechat.pubaccount.message.response.BaseResponse;
import me.own.learn.pubaccount.handler.customEvent.CustomMenuOnClickHandler;
import me.own.learn.pubconfiguration.bo.PubAccountArticleMessageBo;
import me.own.learn.pubconfiguration.service.PubConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/5/31 14:03
 */
@Component(value = "doraemonMenuOnClickHandler")
public class DoraemonYuMenuOnClickHandlerImpl implements CustomMenuOnClickHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoraemonYuMenuOnClickHandlerImpl.class);

    @Autowired
    private PubConfigurationService pubConfigurationService;

    @Override
    public BaseResponse handle(String appId, MessageEntity messageEntity) {
        List<MessageEntity> responseMessageEntityList = new ArrayList<>();
        List<PubAccountArticleMessageBo> hotelMessageBos = pubConfigurationService.getArticleMessageResolver(appId, PubConfigurationService.PubConfigurationEvent.ON_DORAEMON);
        for (PubAccountArticleMessageBo hotelMessageBo : hotelMessageBos) {
            MessageEntity responseMessageEntity = Mapper.Default().map(hotelMessageBo, MessageEntity.class);
            responseMessageEntityList.add(responseMessageEntity);
        }
        LOGGER.debug("handle doraemon event-key content: {}", new ArticlesResponse(responseMessageEntityList.toArray(new MessageEntity[responseMessageEntityList.size()])).toXmlResponseString());
        return new ArticlesResponse(responseMessageEntityList.toArray(new MessageEntity[responseMessageEntityList.size()]));

    }
}
