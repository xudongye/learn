package me.own.learn.customer.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.customer.po.Customer;

/**
 * @author yexudong
 * @date 2019/5/30 16:38
 */
public interface CustomerDao extends BaseDao<Customer> {

    Customer getByOpenId(String openId, long pubaccountId);
}
