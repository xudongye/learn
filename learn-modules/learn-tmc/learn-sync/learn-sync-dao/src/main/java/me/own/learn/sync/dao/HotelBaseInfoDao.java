package me.own.learn.sync.dao;

import me.own.commons.base.dao.BaseDao;
import me.own.learn.sync.po.HotelBaseInfo;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/25 11:34
 */
public interface HotelBaseInfoDao extends BaseDao<HotelBaseInfo> {

    void batchAddHotelInfo(List<HotelBaseInfo> hotelBaseInfos);
}
