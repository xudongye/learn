package me.own.learn.admin.dao.impl;

import me.own.learn.admin.dao.AdminDao;
import me.own.learn.admin.po.Admin;
import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.commons.base.utils.regx.RegxUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/5/28 14:14
 */
@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao {
    @Override
    protected Class<Admin> getEntityClass() {
        return Admin.class;
    }

    @Override
    public Admin getByLoginLabel(String loginLabel) {
        Criteria criteria = getCurrentSession().createCriteria(Admin.class);
        if (RegxUtils.isEmail(loginLabel)) {
            criteria.add(Restrictions.eq("email", loginLabel));
        } else if (RegxUtils.isMobile(loginLabel)) {
            criteria.add(Restrictions.eq("cellphone", loginLabel));
        } else {
            criteria.add(Restrictions.eq("name", loginLabel));
        }
        Admin admin = (Admin) criteria.uniqueResult();
        return admin;
    }

    @Override
    public void batchDelete(List<Long> adminIds) {
        Query query = getCurrentSession().createQuery("update Admin set deleted = true ,modifyTime = :modifyTime where id in :adminIds");
        query.setParameterList("adminIds", adminIds);
        query.setParameter("modifyTime", new Date());
        query.executeUpdate();
    }
}
