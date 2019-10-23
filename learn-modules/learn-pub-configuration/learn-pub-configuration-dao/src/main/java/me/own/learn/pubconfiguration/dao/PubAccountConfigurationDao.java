package me.own.learn.pubconfiguration.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.pubconfiguration.po.PubAccountConfiguration;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/5/31 10:52
 */
public interface PubAccountConfigurationDao extends BaseDao<PubAccountConfiguration> {

    List<String> getDomainsByAppId(String appId);

    PubAccountConfiguration getByAppId(String appId);
}
