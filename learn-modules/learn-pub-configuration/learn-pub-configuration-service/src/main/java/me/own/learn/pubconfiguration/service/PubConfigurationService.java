package me.own.learn.pubconfiguration.service;

import me.own.learn.pubconfiguration.bo.PubAccountArticleMessageBo;
import me.own.learn.pubconfiguration.bo.PubAccountTemplateMessageBo;
import me.own.learn.pubconfiguration.bo.PubAccountTextMessageBo;
import me.own.learn.pubconfiguration.dto.PubConfigurationDto;
import me.own.learn.pubconfiguration.vo.PubAccountMessageVo;
import me.own.learn.pubconfiguration.vo.PubConfigurationVo;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/5/31 10:54
 */
public interface PubConfigurationService {

    interface PubConfigurationEvent {
        String ON_SUBSCRIBE = "onSubscribe";
        String ON_DORAEMON = "doraemon";
    }

    //configuration
    PubConfigurationVo create(PubConfigurationDto pubConfigurationDto);

    PubConfigurationVo update(PubConfigurationDto pubConfigurationDto);

    List<String> domains(String appId);

    PubConfigurationVo getById(long pubAccountId);

    List<PubConfigurationVo> getActivePubAccountConfiguration();

    //message
    PubAccountMessageVo getMessageContent(String appId, String event);

    PubAccountTextMessageBo getTextMessageResolver(String appId, String event);

    List<PubAccountArticleMessageBo> getArticleMessageResolver(String appId, String event);

    PubAccountTemplateMessageBo getTemplateMessageResolver(String appId, String event);

    //menu
    String queryMenuContentByAppId(String appId);

}
