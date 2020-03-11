package me.own.learn.mall.market.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.market.dao.HomeAdvertiseDao;
import me.own.learn.mall.market.exception.MallAdvertiseNotFoundException;
import me.own.learn.mall.market.po.HomeAdvertise;
import me.own.learn.mall.market.service.HomeAdvertiseQueryCondition;
import me.own.learn.mall.market.service.HomeAdvertiseService;
import me.own.learn.mall.market.vo.HomeAdvertiseVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/10 15:34
 */
@Service
public class HomeAdvertiseServiceImpl implements HomeAdvertiseService {

    @Autowired
    private HomeAdvertiseDao advertiseDao;

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<HomeAdvertiseVo> page(int pageNum, int pageSize, HomeAdvertiseQueryCondition condition) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(HomeAdvertise.class);

        if (condition != null) {
            if (condition.getType() != null) {
                query.setSimpleCondition("type", condition.getType() + "", QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getName() != null) {
                query.setSimpleCondition("name", condition.getName(), QueryConstants.SimpleQueryMode.Like);
            }
            //todo time
        }
        PageQueryResult<HomeAdvertise> result = advertiseDao.pageQuery(pageNum, pageSize, query);
        return result.mapItems(HomeAdvertiseVo.class);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<HomeAdvertiseVo> listValid() {
        QueryCriteriaUtil query = new QueryCriteriaUtil(HomeAdvertise.class);
        query.setSimpleCondition("status", 1 + "", QueryConstants.SimpleQueryMode.Equal);
        List<HomeAdvertise> advertises = advertiseDao.filter(query, null, new QueryOrder("sort", QueryOrder.DESC));
        if (CollectionUtils.isNotEmpty(advertises)) {
            return Mapper.Default().mapArray(advertises, HomeAdvertiseVo.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public HomeAdvertiseVo getById(long id) {
        HomeAdvertise advertise = advertiseDao.get(id);
        if (advertise == null) {
            throw new MallAdvertiseNotFoundException();
        }
        return Mapper.Default().map(advertise, HomeAdvertiseVo.class);
    }
}
