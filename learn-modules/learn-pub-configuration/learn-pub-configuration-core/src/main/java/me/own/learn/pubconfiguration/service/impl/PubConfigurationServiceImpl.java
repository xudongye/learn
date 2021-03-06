package me.own.learn.pubconfiguration.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.commons.wechat.pubaccount.common.ConfigureManager;
import me.own.learn.pubconfiguration.bo.PubAccountArticleMessageBo;
import me.own.learn.pubconfiguration.bo.PubAccountTemplateMessageBo;
import me.own.learn.pubconfiguration.bo.PubAccountTextMessageBo;
import me.own.learn.pubconfiguration.dao.PubAccountConfigurationDao;
import me.own.learn.pubconfiguration.dao.PubAccountMenuDao;
import me.own.learn.pubconfiguration.dao.PubAccountMessageDao;
import me.own.learn.pubconfiguration.dto.PubConfigurationDto;
import me.own.learn.pubconfiguration.exception.PubConfigurationExistException;
import me.own.learn.pubconfiguration.exception.PubConfigurationNotFoundException;
import me.own.learn.pubconfiguration.po.PubAccountConfiguration;
import me.own.learn.pubconfiguration.po.PubAccountMenu;
import me.own.learn.pubconfiguration.po.PubAccountMessage;
import me.own.learn.pubconfiguration.service.PubConfigurationQueryCondition;
import me.own.learn.pubconfiguration.service.PubConfigurationService;
import me.own.learn.pubconfiguration.vo.PubAccountMenuVo;
import me.own.learn.pubconfiguration.vo.PubAccountMessageVo;
import me.own.learn.pubconfiguration.vo.PubConfigurationVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/5/31 10:57
 */
@Service
public class PubConfigurationServiceImpl implements PubConfigurationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PubConfigurationServiceImpl.class);

    @Autowired
    private PubAccountConfigurationDao pubAccountConfigurationDao;

    @Autowired
    private PubAccountMessageDao pubAccountMessageDao;

    @Autowired
    private PubAccountMenuDao pubAccountMenuDao;

    public final ObjectMapper mapper = new ObjectMapper();

    @Override
    @Transactional
    public PubConfigurationVo create(PubConfigurationDto pubConfigurationDto) {
        if (pubAccountExist(pubConfigurationDto.getPubAccountAppId())) {
            throw new PubConfigurationExistException();
        }
        PubAccountConfiguration pubAccountConfiguration = Mapper.Default().map(pubConfigurationDto, PubAccountConfiguration.class);
        pubAccountConfiguration.setCreateTime(new Date());
        pubAccountConfiguration.setActive(true);
        pubAccountConfigurationDao.create(pubAccountConfiguration);
        LOGGER.info("create new pub account {} configuration", pubAccountConfiguration.getName());
        return Mapper.Default().map(pubAccountConfiguration, PubConfigurationVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<PubConfigurationVo> page(int pageNumber, int pageSize, PubConfigurationQueryCondition condition) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(PubAccountConfiguration.class);
        query.setDeletedFalseCondition();
        if (condition != null) {
            if (condition.getName() != null) {
                query.setSimpleCondition("name", condition.getName(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getDomain() != null) {
                query.setSimpleCondition("domain", condition.getDomain(), QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getPubAccountAppId() != null) {
                query.setSimpleCondition("pubAccountAppId", condition.getPubAccountAppId(), QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getActive() != null) {
                query.setSimpleCondition("active", condition.getActive() + "", QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getWxpayKey() != null) {
                query.setSimpleCondition("wxpayKey", condition.getWxpayKey(), QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getWxpayMerchantId() != null) {
                query.setSimpleCondition("wxpayMerchantId", condition.getWxpayMerchantId(), QueryConstants.SimpleQueryMode.Equal);
            }
        }
        PageQueryResult<PubAccountConfiguration> pageQueryResult = pubAccountConfigurationDao.pageQuery(pageNumber, pageSize, query);

        return pageQueryResult.mapItems(PubConfigurationVo.class);
    }

    private boolean pubAccountExist(String pubAccountAppId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(PubAccountConfiguration.class);
        query.setSimpleCondition("pubAccountAppId", pubAccountAppId, QueryConstants.SimpleQueryMode.Equal);
        return pubAccountConfigurationDao.getCount(query) > 0;
    }

    @Override
    @Transactional
    public PubConfigurationVo update(PubConfigurationDto pubConfigurationDto) {
        PubAccountConfiguration pubAccountConfiguration = pubAccountConfigurationDao.get(pubConfigurationDto.getId());
        if (pubAccountConfiguration == null) {
            throw new PubConfigurationNotFoundException();
        }
        if (pubConfigurationDto.getActive() != null) {
            pubAccountConfiguration.setActive(pubConfigurationDto.getActive());
        }
        if (pubConfigurationDto.getDomain() != null) {
            pubAccountConfiguration.setDomain(pubConfigurationDto.getDomain());
        }
        if (pubConfigurationDto.getLogo() != null) {
            pubAccountConfiguration.setLogo(pubConfigurationDto.getLogo());
        }
        if (pubConfigurationDto.getName() != null) {
            pubAccountConfiguration.setName(pubConfigurationDto.getName());
        }
        if (pubConfigurationDto.getPubAccountAppId() != null) {
            pubAccountConfiguration.setPubAccountAppId(pubConfigurationDto.getPubAccountAppId());
        }
        if (pubConfigurationDto.getPubAccountAppSecret() != null) {
            pubAccountConfiguration.setPubAccountAppSecret(pubConfigurationDto.getPubAccountAppSecret());
        }
        if (pubConfigurationDto.getPubAccountToken() != null) {
            pubAccountConfiguration.setPubAccountToken(pubConfigurationDto.getPubAccountToken());
        }
        if (pubConfigurationDto.getWxpayKey() != null) {
            pubAccountConfiguration.setWxpayKey(pubConfigurationDto.getWxpayKey());
        }
        if (pubConfigurationDto.getWxpayMerchantId() != null) {
            pubAccountConfiguration.setWxpayMerchantId(pubConfigurationDto.getWxpayMerchantId());
        }
        pubAccountConfiguration.setModifyTime(new Date());
        pubAccountConfigurationDao.update(pubAccountConfiguration);
        return Mapper.Default().map(pubAccountConfiguration, PubConfigurationVo.class);
    }

    @Override
    @Transactional
    public List<String> domains(String appId) {
        return pubAccountConfigurationDao.getDomainsByAppId(appId);
    }

    @Override
    @Transactional(readOnly = true)
    public PubConfigurationVo getById(long pubAccountId) {
        PubAccountConfiguration pubAccountConfiguration = pubAccountConfigurationDao.get(pubAccountId);
        if (pubAccountConfiguration == null) {
            throw new PubConfigurationNotFoundException();
        }
        return Mapper.Default().map(pubAccountConfiguration, PubConfigurationVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PubConfigurationVo getByAppId(String appId) {
        PubAccountConfiguration configuration = pubAccountConfigurationDao.getByAppId(appId);
        if (configuration != null) {
            return Mapper.Default().map(configuration, PubConfigurationVo.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PubConfigurationVo> getActivePubAccountConfiguration() {
        QueryCriteriaUtil query = new QueryCriteriaUtil(PubAccountConfiguration.class);
        query.setSimpleCondition("active", true + "", QueryConstants.SimpleQueryMode.Equal);
        List<PubAccountConfiguration> pubAccountConfigurations = pubAccountConfigurationDao.getAll(null, null);
        return Mapper.Default().mapArray(pubAccountConfigurations, PubConfigurationVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PubAccountMessageVo getMessageContent(String appId, String event) {
        PubAccountMessage pubAccountMessage = pubAccountMessageDao.getByAppIdAndEvent(appId, event);
        if (pubAccountMessage != null) {
            return Mapper.Default().map(pubAccountMessage, PubAccountMessageVo.class);
        }
        return null;
    }

    @Override
    @Transactional
    public PubAccountTextMessageBo getTextMessageResolver(String appId, String event) {
        PubAccountMessageVo pubAccountMessageVo = this.getMessageContent(appId, event);
        if (pubAccountMessageVo != null) {
            String content = pubAccountMessageVo.getContent();
            PubAccountTextMessageBo textMessage = new PubAccountTextMessageBo();
            textMessage.setValue(content);
            return textMessage;
        }
        return null;
    }

    @Override
    @Transactional
    public List<PubAccountArticleMessageBo> getArticleMessageResolver(String appId, String event) {
        PubAccountMessageVo pubAccountMessageVo = this.getMessageContent(appId, event);
        if (pubAccountMessageVo != null) {
            String content = pubAccountMessageVo.getContent();
            try {
                return mapper.readValue(
                        content, new TypeReference<List<PubAccountArticleMessageBo>>() {
                        });
            } catch (IOException io) {
                io.printStackTrace();
                LOGGER.error("FATAL:public account configuration initialize error.", io);
            }
        }
        return null;
    }

    @Override
    @Transactional
    public PubAccountTemplateMessageBo getTemplateMessageResolver(String appId, String event) {
        PubAccountMessageVo pubAccountMessageVo = this.getMessageContent(appId, event);
        if (pubAccountMessageVo != null) {
            String content = pubAccountMessageVo.getContent();
            try {
                return mapper.readValue(content, PubAccountTemplateMessageBo.class);
            } catch (IOException io) {
                io.printStackTrace();
                LOGGER.error("FATAL:public account configuration initialize error.", io);
            }
        }
        return null;
    }

    @Override
    @Transactional
    public String queryMenuContentByAppId(String appId) {
        PubAccountMenu pubAccountMenu = pubAccountMenuDao.getByAppId(appId);
        if (pubAccountMenu != null) {
            return pubAccountMenu.getContent();
        }
        return null;
    }

    @Override
    @Transactional
    public boolean onActive(long id) {
        PubAccountConfiguration configuration = pubAccountConfigurationDao.get(id);
        if (configuration == null || configuration.getDeleted()) {
            throw new PubConfigurationNotFoundException();
        }
        configuration.setActive(!configuration.getActive());
        configuration.setModifyTime(new Date());
        pubAccountConfigurationDao.update(configuration);
        ConfigureManager.removeConfigure(configuration.getPubAccountAppId());
        return configuration.getActive();
    }
}
