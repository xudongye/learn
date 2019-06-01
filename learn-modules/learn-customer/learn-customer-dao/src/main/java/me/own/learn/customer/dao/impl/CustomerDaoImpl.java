package me.own.learn.customer.dao.impl;

import me.own.commons.base.dao.impl.BaseDaoImpl;
import me.own.learn.customer.dao.CustomerDao;
import me.own.learn.customer.po.Customer;
import org.springframework.stereotype.Repository;

/**
 * @author yexudong
 * @date 2019/5/30 16:38
 */
@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {
    @Override
    protected Class<Customer> getEntityClass() {
        return Customer.class;
    }
}
