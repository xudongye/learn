package me.own.learn.pubaccount.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.commons.wechat.pubaccount.customservice.CustomServiceMessageSendService;
import me.own.commons.wechat.pubaccount.customservice.TemplateServiceMessageSendService;
import me.own.commons.wechat.pubaccount.customservice.impl.CustomServiceMessageSendServiceImpl;
import me.own.commons.wechat.pubaccount.customservice.impl.TemplateServiceMessageSendServiceImpl;
import me.own.commons.wechat.pubaccount.message.MessageEntity;
import me.own.commons.wechat.pubaccount.templateMessage.TemplateMessageEntity;
import me.own.learn.customer.service.CustomerService;
import me.own.learn.customer.vo.CustomerVo;
import me.own.learn.pubaccount.service.PubAccountNotifyCustomerService;
import me.own.learn.pubaccount.service.model.ArticleWeChatMessageEntity;
import me.own.learn.pubconfiguration.service.PubConfigurationService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PubAccountNotifyCustomerServiceImpl implements PubAccountNotifyCustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PubAccountNotifyCustomerServiceImpl.class);

    private CustomServiceMessageSendService customServiceMessageSendService = new CustomServiceMessageSendServiceImpl();

    private TemplateServiceMessageSendService templateServiceMessageSendService = new TemplateServiceMessageSendServiceImpl();

    @Autowired
    private CustomerService customerService;
    @Autowired
    private PubConfigurationService pubConfigurationService;


    @Override
    public void notifyCustomerText(String appId, String openId, String textMsg) {
        customServiceMessageSendService.sendText(appId, openId, textMsg);
    }

    @Override
    public void notifyCustomerText(long customerId, String textMsg) {
        GetPubAccountAppIdOpenId getPubAccountAppIdOpenId = new GetPubAccountAppIdOpenId(customerId).invoke();
        String openid = getPubAccountAppIdOpenId.getOpenid();
        String pubAccountAppId = getPubAccountAppIdOpenId.getPubAccountAppId();
        if (StringUtils.isNotEmpty(openid)) {
            notifyCustomerText(pubAccountAppId, openid, textMsg);
        }
    }

    @Override
    public void notifyCustomerArticle(String appId, String openId, List<ArticleWeChatMessageEntity> articleWeChatMessageEntities) {
        customServiceMessageSendService.sendNews(appId, openId,
                Mapper.Default().mapArray(
                        articleWeChatMessageEntities,
                        MessageEntity.class
                ).toArray(new MessageEntity[articleWeChatMessageEntities.size()])
        );
    }

    @Override
    public void notifyCustomerArticle(long customerId, List<ArticleWeChatMessageEntity> articleWeChatMessageEntities) {
        GetPubAccountAppIdOpenId getPubAccountAppIdOpenId = new GetPubAccountAppIdOpenId(customerId).invoke();
        String openid = getPubAccountAppIdOpenId.getOpenid();
        String pubAccountAppId = getPubAccountAppIdOpenId.getPubAccountAppId();
        if (StringUtils.isNotEmpty(openid)) {
            notifyCustomerArticle(pubAccountAppId, openid, articleWeChatMessageEntities);
        }
    }

    @Override
    public void notifyCustomerTemplateMessageText(String appId, String openid, TemplateMessageEntity messageEntity) {
        messageEntity.setTouser(openid);
        try {
            String message = new ObjectMapper().writeValueAsString(messageEntity);
            templateServiceMessageSendService.sendMessage(appId, message);
            LOGGER.debug("send template message to pub account customer {}, with message {}", openid, message);
        } catch (JsonProcessingException e) {
            LOGGER.debug("send template message error to pub account customer {}", openid);
        }
    }

    @Override
    public void notifyCustomerTemplateMessageText(long customerId, TemplateMessageEntity messageEntity) {
        GetPubAccountAppIdOpenId getPubAccountAppIdOpenId = new GetPubAccountAppIdOpenId(customerId).invoke();
        String openid = getPubAccountAppIdOpenId.getOpenid();
        String pubAccountAppId = getPubAccountAppIdOpenId.getPubAccountAppId();
        if (StringUtils.isNotEmpty(openid)) {
            notifyCustomerTemplateMessageText(pubAccountAppId, openid, messageEntity);
        }
    }

    private class GetPubAccountAppIdOpenId {
        private long customerId;
        private String openid;
        private String pubAccountAppId;

        public GetPubAccountAppIdOpenId(long customerId) {
            this.customerId = customerId;
        }

        public String getOpenid() {
            return openid;
        }

        public String getPubAccountAppId() {
            return pubAccountAppId;
        }

        public GetPubAccountAppIdOpenId invoke() {
            CustomerVo customer = customerService.getById(customerId);
            openid = customer.getOpenid();
            Long pubAccountId = customer.getPubAccountId();
            pubAccountAppId = pubConfigurationService.getById(pubAccountId).getPubAccountAppId();
            return this;
        }
    }
}
