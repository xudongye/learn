package me.own.learn.store.product.service;

import me.own.learn.store.product.dto.PropertyItemDto;
import me.own.learn.store.product.vo.PropertyItemVo;

import java.util.List;

public interface PropertyItemService {

    PropertyItemVo create(PropertyItemDto propertyItemDto);

    PropertyItemVo update(PropertyItemDto propertyItemDto);

    void delete(long id);

    List<PropertyItemVo> listGroupByProductId(long productId);
}
