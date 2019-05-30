package me.own.learn.customer.service;

import me.own.learn.commons.base.dao.PageQueryResult;
import me.own.learn.customer.dto.CustomerDto;
import me.own.learn.customer.vo.CustomerVo;

/**
 * @author yexudong
 * @date 2019/5/30 16:44
 */
public interface CustomerService {

    CustomerVo createFromPubAccount(CustomerDto customerDto);

    CustomerVo createFromUnName();

    CustomerVo createFromCellphone(String cellphone);

    CustomerVo complete(CustomerDto customerDto);

    PageQueryResult<CustomerVo> page(int pageNum, int pageSize, CustomerQueryCondition customerQueryCondition);
}
