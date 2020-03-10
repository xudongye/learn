package me.own.learn.mall.market.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.mall.market.vo.HomeAdvertiseVo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/10 15:27
 */
public interface HomeAdvertiseService {

    PageQueryResult<HomeAdvertiseVo> page(int pageNum, int pageSize, HomeAdvertiseQueryCondition condition);

    /**
     * 查询所有有效期广告
     *
     * @return
     */
    List<HomeAdvertiseVo> listValid();


    HomeAdvertiseVo getById(long id);
}
