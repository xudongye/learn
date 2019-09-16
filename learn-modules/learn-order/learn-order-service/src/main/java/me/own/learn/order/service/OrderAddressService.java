package me.own.learn.order.service;

import me.own.learn.order.dto.OrderAddressDto;
import me.own.learn.order.vo.OrderAddressVo;

import java.util.List;

public interface OrderAddressService {

    OrderAddressVo create(OrderAddressDto addressDto);

    //设置常用地址
    OrderAddressVo setDefaultAddress(Long addressNo);

    List<OrderAddressVo> listAddressByCustomerId(long customerId);
}
