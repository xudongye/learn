package me.own.learn.pubconfiguration.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.pubconfiguration.dao.PubAccountMessageDao;
import me.own.learn.pubconfiguration.po.PubAccountMessage;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.annotations.OrderBy;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/5/31 11:33
 */
@Repository
public class PubAccountMessageDaoImpl extends BaseDaoImpl<PubAccountMessage> implements PubAccountMessageDao {
    @Override
    protected Class<PubAccountMessage> getEntityClass() {
        return PubAccountMessage.class;
    }

    @Override
    public PubAccountMessage getByAppIdAndEvent(String appId, String event) {
        Criteria criteria = getCurrentSession().createCriteria(PubAccountMessage.class);
        criteria.add(Restrictions.eq("appId", appId));
        criteria.add(Restrictions.eq("event", event));
        criteria.add(Restrictions.eq("deleted", false));
        criteria.addOrder(Order.asc("createTime"));
        List<PubAccountMessage> messages = criteria.list();
        return CollectionUtils.isNotEmpty(messages) ? messages.get(0) : null;
    }
}
