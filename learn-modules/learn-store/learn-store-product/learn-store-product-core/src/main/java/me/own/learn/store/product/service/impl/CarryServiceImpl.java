package me.own.learn.store.product.service.impl;

import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.store.product.dao.CarryDao;
import me.own.learn.store.product.dto.CarryDto;
import me.own.learn.store.product.exception.CarryNotFoundException;
import me.own.learn.store.product.po.Carry;
import me.own.learn.store.product.service.CarryService;
import me.own.learn.store.product.vo.CarryVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CarryServiceImpl implements CarryService {


    private static final Logger LOGGER = LoggerFactory.getLogger(CarryServiceImpl.class);

    @Autowired
    private CarryDao carryDao;

    @Override
    @Transactional
    public CarryVo create(CarryDto carryDto) {
        Carry carry = Mapper.Default().map(carryDto, Carry.class);
        carry.setCreateTime(new Date());
        carry.setDeleted(false);
        carryDao.create(carry);
        LOGGER.info("create new carry name {}", carry.getCarryName());
        return Mapper.Default().map(carry, CarryVo.class);
    }

    @Override
    public CarryVo update(CarryDto carryDto) {
        return null;
    }

    @Override
    @Transactional
    public void delete(long id) {
        Carry carry = carryDao.get(id);
        if (carry == null || carry.getDeleted()) {
            throw new CarryNotFoundException();
        }
        carry.setDeleted(true);
        carry.setModifyTime(new Date());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarryVo> listGroupByProductId(long productId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Carry.class);
        query.setDeletedFalseCondition();
//        query.setSimpleCondition("p");
        return null;
    }
}
