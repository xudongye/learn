package me.own.learn.customer.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.customer.dao.CustomerDao;
import me.own.learn.customer.dto.CustomerDto;
import me.own.learn.customer.exception.CustomerNotFoundException;
import me.own.learn.customer.po.Customer;
import me.own.learn.customer.service.CustomerQueryCondition;
import me.own.learn.customer.service.CustomerService;
import me.own.learn.customer.vo.CustomerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/5/30 16:47
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerDao customerDao;

    @Override
    @Transactional(readOnly = true)
    public CustomerVo getById(long customerId) {
        Customer customer = customerDao.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        return Mapper.Default().map(customer, CustomerVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerVo getByOpenId(String openId, long pubaccountId) {
        Customer customer = customerDao.getByOpenId(openId, pubaccountId);
        if (customer != null) {
            return Mapper.Default().map(customer, CustomerVo.class);
        }
        return null;
    }

    @Override
    @Transactional
    public CustomerVo createFromPubAccount(CustomerDto customerDto) {
        Customer customer = Mapper.Default().map(customerDto, Customer.class);
        customer.setCreateTime(new Date());
        customerDao.create(customer);
        LOGGER.info("create customer {} source {}", customer.getNickName(), customer.getSource());
        return Mapper.Default().map(customer, CustomerVo.class);
    }

    @Override
    @Transactional
    public void updateSubscribeStatus(long customerId, boolean subscribe) {
        Customer customer = customerDao.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        customer.setSubscribed(subscribe);
        LOGGER.info("customer {} update pub account subscribe status {}", customerId, subscribe);
    }

    @Override
    @Transactional
    public CustomerVo createFromUnName() {
        Customer customer = new Customer();
        customer.setCreateTime(new Date());
        customer.setSource("unname");
        customerDao.create(customer);
        customer.setNickName("u_" + customer.getId());
        LOGGER.info("create a unname customer {}", customer.getNickName());
        return Mapper.Default().map(customer, CustomerVo.class);
    }

    @Override
    @Transactional
    public CustomerVo createFromCellphone(String cellphone) {
        Customer customer = new Customer();
        customer.setCellphone(cellphone);
        customer.setSource("phone");
        customer.setNickName(cellphone);
        customer.setCreateTime(new Date());
        customerDao.create(customer);
        LOGGER.info("create a customer {} from cellphone", customer.getNickName());
        return Mapper.Default().map(customer, CustomerVo.class);
    }

    @Override
    public CustomerVo complete(CustomerDto customerDto) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<CustomerVo> page(int pageNum, int pageSize, CustomerQueryCondition customerQueryCondition) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Customer.class);
        if (customerQueryCondition != null) {
            if (customerQueryCondition.getCellphone() != null) {
                query.setSimpleCondition("cellphone", customerQueryCondition.getCellphone(), QueryConstants.SimpleQueryMode.Equal);
            }
            if (customerQueryCondition.getNickName() != null) {
                query.setSimpleCondition("nickName", customerQueryCondition.getNickName(), QueryConstants.SimpleQueryMode.Like);
            }
            if (customerQueryCondition.getEmail() != null) {
                query.setSimpleCondition("email", customerQueryCondition.getEmail(), QueryConstants.SimpleQueryMode.Equal);
            }
            if (customerQueryCondition.getCountry() != null) {
                query.setSimpleCondition("country", customerQueryCondition.getCountry(), QueryConstants.SimpleQueryMode.Like);
            }
            if (customerQueryCondition.getProvince() != null) {
                query.setSimpleCondition("province", customerQueryCondition.getProvince(), QueryConstants.SimpleQueryMode.Like);
            }
            if (customerQueryCondition.getCity() != null) {
                query.setSimpleCondition("city", customerQueryCondition.getCity(), QueryConstants.SimpleQueryMode.Like);
            }
            if (customerQueryCondition.getSource() != null) {
                query.setSimpleCondition("source", customerQueryCondition.getSource(), QueryConstants.SimpleQueryMode.Equal);
            }
            if (customerQueryCondition.getSex() != null) {
                query.setSimpleCondition("sex", customerQueryCondition.getSex(), QueryConstants.SimpleQueryMode.Equal);
            }
        }
        List<QueryOrder> orders = new ArrayList<>();
        QueryOrder order = new QueryOrder();
        order.setColumnName("createTime");
        order.setOder(QueryOrder.DESC);
        PageQueryResult<Customer> result = customerDao.pageQuery(pageNum, pageSize, query, orders);
        return result.mapItems(CustomerVo.class);
    }
}
