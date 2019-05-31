package me.own.learn.pubconfiguration.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.pubconfiguration.dao.PubAccountMenuDao;
import me.own.learn.pubconfiguration.po.PubAccountMenu;
import me.own.learn.pubconfiguration.po.PubAccountMessage;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/5/31 15:05
 */
@Repository
public class PubAccountMenuDaoImpl extends BaseDaoImpl<PubAccountMenu> implements PubAccountMenuDao {
    @Override
    protected Class<PubAccountMenu> getEntityClass() {
        return PubAccountMenu.class;
    }

    @Override
    public PubAccountMenu getByAppId(String appId) {
        Criteria criteria = getCurrentSession().createCriteria(PubAccountMenu.class);
        criteria.add(Restrictions.eq("appId", appId));
        return (PubAccountMenu) criteria.uniqueResult();
    }
}
