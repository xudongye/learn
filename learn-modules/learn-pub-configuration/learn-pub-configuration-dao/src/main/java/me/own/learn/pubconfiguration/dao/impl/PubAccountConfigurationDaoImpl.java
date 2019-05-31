package me.own.learn.pubconfiguration.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.pubconfiguration.dao.PubAccountConfigurationDao;
import me.own.learn.pubconfiguration.po.PubAccountConfiguration;
import org.springframework.stereotype.Repository;

/**
 * @author yexudong
 * @date 2019/5/31 10:52
 */
@Repository
public class PubAccountConfigurationDaoImpl extends BaseDaoImpl<PubAccountConfiguration> implements PubAccountConfigurationDao {
    @Override
    protected Class<PubAccountConfiguration> getEntityClass() {
        return PubAccountConfiguration.class;
    }
}
