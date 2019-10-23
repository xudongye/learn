package me.own.learn.customer.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.customer.dto.CustomerDto;
import me.own.learn.customer.vo.CustomerVo;

/**
 * @author yexudong
 * @date 2019/5/30 16:44
 */
public interface CustomerService {

    CustomerVo getById(long customerId);

    CustomerVo getByOpenId(String openId, long pubaccountId);

    CustomerVo createFromPubAccount(CustomerDto customerDto);

    /**
     * 更新客户的微信公众号订阅状态
     * @param customerId customer.id
     * @param subscribe 是否订阅
     */
    void updateSubscribeStatus(long customerId, boolean subscribe);

    CustomerVo createFromUnName();

    CustomerVo createFromCellphone(String cellphone);

    CustomerVo complete(CustomerDto customerDto);

    PageQueryResult<CustomerVo> page(int pageNum, int pageSize, CustomerQueryCondition customerQueryCondition);
}
