package me.own.learn.store.product.service;

import me.own.learn.store.product.dto.CarryDto;
import me.own.learn.store.product.vo.CarryVo;

import java.util.List;

public interface CarryService {

    CarryVo create(CarryDto carryDto);

    CarryVo update(CarryDto carryDto);

    void delete(long id);

    List<CarryVo> listGroupByProductId(long productId);
}
