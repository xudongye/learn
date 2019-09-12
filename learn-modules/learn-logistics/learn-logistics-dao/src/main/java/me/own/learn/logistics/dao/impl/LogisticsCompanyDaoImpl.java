package me.own.learn.logistics.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.logistics.dao.LogisticsCompanyDao;
import me.own.learn.logistics.po.LogisticsCompany;
import org.springframework.stereotype.Repository;

/**
 * @author yexudong
 * @date 2019/9/2 14:02
 */
@Repository
public class LogisticsCompanyDaoImpl extends BaseDaoImpl<LogisticsCompany> implements LogisticsCompanyDao {
    @Override
    protected Class<LogisticsCompany> getEntityClass() {
        return LogisticsCompany.class;
    }
}
