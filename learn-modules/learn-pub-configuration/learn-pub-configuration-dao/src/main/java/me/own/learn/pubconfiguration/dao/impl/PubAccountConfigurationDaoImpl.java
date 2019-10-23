package me.own.learn.pubconfiguration.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.pubconfiguration.dao.PubAccountConfigurationDao;
import me.own.learn.pubconfiguration.po.PubAccountConfiguration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<String> getDomainsByAppId(String appId) {
        Criteria criteria = getCurrentSession().createCriteria(PubAccountConfiguration.class);
        criteria.add(Restrictions.eq("appId", appId));
        criteria.setProjection(Projections.distinct(Property.forName("domain")));
        return criteria.list();
    }

    @Override
    public PubAccountConfiguration getByAppId(String appId) {
        Criteria criteria = getCurrentSession().createCriteria(PubAccountConfiguration.class);
        criteria.add(Restrictions.eq("pubAccountAppId", appId));
        criteria.add(Restrictions.eq("active", true));
        return (PubAccountConfiguration) criteria.uniqueResult();
    }
}
