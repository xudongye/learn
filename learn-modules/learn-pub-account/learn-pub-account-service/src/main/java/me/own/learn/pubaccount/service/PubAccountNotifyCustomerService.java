package me.own.learn.pubaccount.service;

import me.own.commons.wechat.pubaccount.templateMessage.TemplateMessageEntity;
import me.own.learn.pubaccount.service.model.ArticleWeChatMessageEntity;

import java.util.List;

public interface PubAccountNotifyCustomerService {
    /**
     * 用纯文本消息通知微信用户
     * @param appId
     * @param openId
     * @param textMsg
     */
    void notifyCustomerText(String appId, String openId, String textMsg);

    void notifyCustomerText(long customerId, String textMsg);

    /**
     * 用图文消息通知微信用户
     * @param appId
     * @param openId
     * @param articleWeChatMessageEntities
     */
    void notifyCustomerArticle(String appId, String openId, List<ArticleWeChatMessageEntity> articleWeChatMessageEntities);

    void notifyCustomerArticle(long customerId, List<ArticleWeChatMessageEntity> articleWeChatMessageEntities);

    /***
     * 使用模板消息通知微信用户
     * @param appId
     * @param openid
     * @param messageEntity
     */
    void notifyCustomerTemplateMessageText(String appId, String openid, TemplateMessageEntity messageEntity);

    void notifyCustomerTemplateMessageText(long customerId, TemplateMessageEntity messageEntity);
}
