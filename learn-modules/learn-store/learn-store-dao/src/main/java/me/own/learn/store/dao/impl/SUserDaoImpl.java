package me.own.learn.store.dao.impl;

import me.own.learn.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.commons.base.utils.encode.MD5;
import me.own.learn.store.dao.SUserDao;
import me.own.learn.store.po.SUser;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author yexudong
 * @date 2019/5/15 14:25
 */
@Repository
public class SUserDaoImpl extends BaseDaoImpl<SUser> implements SUserDao {
    @Override
    protected Class<SUser> getEntityClass() {
        return SUser.class;
    }

    @Override
    public SUser getByUsernameAndPassword(String username, String password) {
        Criteria criteria = getCurrentSession().createCriteria(SUser.class);
        criteria.add(Restrictions.eq("username", username));
        criteria.add(Restrictions.eq("password", MD5.getMD5Code(password)));
        SUser sUser = (SUser) criteria.uniqueResult();
        return sUser;
    }
}
