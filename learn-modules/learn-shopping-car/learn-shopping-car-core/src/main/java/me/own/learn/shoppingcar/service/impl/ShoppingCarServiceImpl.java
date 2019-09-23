package me.own.learn.shoppingcar.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.shoppingcar.dao.ShoppingCarDao;
import me.own.learn.shoppingcar.dto.ShoppingCarDto;
import me.own.learn.shoppingcar.po.ShoppingCar;
import me.own.learn.shoppingcar.service.ShoppingCarService;
import me.own.learn.shoppingcar.vo.ShoppingCarVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCarServiceImpl.class);

    @Autowired
    private ShoppingCarDao shoppingCarDao;

    @Override
    @Transactional
    public ShoppingCarVo create(ShoppingCarDto shoppingCarDto) {
        ShoppingCar shoppingCar = Mapper.Default().map(shoppingCarDto, ShoppingCar.class);
        shoppingCar.onCreated();
        shoppingCarDao.create(shoppingCar);
        LOGGER.info("shopping car be created:{}", shoppingCar.getId());
        return Mapper.Default().map(shoppingCar, ShoppingCarVo.class);
    }

    @Override
    public ShoppingCarVo update(ShoppingCarDto shoppingCarDto) {
        return null;
    }

    @Override
    public void batchDelete(List<Long> ids) {

    }


    @Override
    public PageQueryResult<ShoppingCarVo> page(int pageNum, int pageSize, long customerId) {
        return null;
    }
}
