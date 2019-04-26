package me.own.learn.sync.dao;

import me.own.learn.commons.base.dao.BaseDao;
import me.own.learn.sync.po.HotelId;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/24 17:49
 */
public interface HotelIdDao extends BaseDao<HotelId> {

    void batchAddHotelIds(List<Long> hotelIds, String cityCode);

    List<Long> getIdsByCityCode(String cityCode);

    void updateNeedSync(List<Long> hotelIds);
}
