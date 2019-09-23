package me.own.learn.order.service.impl;

import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.order.dao.OrderAddressDao;
import me.own.learn.order.dto.OrderAddressDto;
import me.own.learn.order.exception.OrderAddressNotFoundException;
import me.own.learn.order.po.OrderAddress;
import me.own.learn.order.service.OrderAddressService;
import me.own.learn.order.vo.OrderAddressVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderAddressServiceImpl implements OrderAddressService {

    @Autowired
    private OrderAddressDao addressDao;

    @Override
    @Transactional
    public OrderAddressVo create(OrderAddressDto addressDto) {
        OrderAddress address = Mapper.Default().map(addressDto, OrderAddress.class);
        address.setCreateTime(new Date());
        addressDao.create(address);
        return Mapper.Default().map(address, OrderAddressVo.class);
    }

    @Override
    @Transactional
    public OrderAddressVo setDefaultAddress(Long addressNo) {
        OrderAddress address = addressDao.get(addressNo);
        if (address == null || address.getDeleted()) {
            throw new OrderAddressNotFoundException();
        }
        //todo
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderAddressVo> listAddressByCustomerId(long customerId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(OrderAddress.class);
        query.setSimpleCondition("customerId", customerId + "", QueryConstants.SimpleQueryMode.Equal);
        List<OrderAddress> addresses = addressDao.filter(query, null, new QueryOrder("createTime", QueryOrder.DESC));
        if (CollectionUtils.isNotEmpty(addresses)) {
            return Mapper.Default().mapArray(addresses, OrderAddressVo.class);
        }
        return null;
    }
}
