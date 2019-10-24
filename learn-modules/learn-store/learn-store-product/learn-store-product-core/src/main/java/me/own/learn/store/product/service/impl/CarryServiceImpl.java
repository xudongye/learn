package me.own.learn.store.product.service.impl;

import me.own.learn.store.product.dto.CarryDto;
import me.own.learn.store.product.service.CarryService;
import me.own.learn.store.product.vo.CarryVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarryServiceImpl implements CarryService {



    @Override
    public CarryVo create(CarryDto carryDto) {
        return null;
    }

    @Override
    public CarryVo update(CarryDto carryDto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<CarryVo> listGroupByProductId(long productId) {
        return null;
    }
}
