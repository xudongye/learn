package me.own.learn.mall.market.service.impl;

import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.market.dao.MallPreferenceAreaDao;
import me.own.learn.mall.market.po.MallPreferenceArea;
import me.own.learn.mall.market.service.MallPreferenceAreaService;
import me.own.learn.mall.market.vo.MallPreferenceAreaVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/9 17:36
 */
@Service
public class MallPreferenceAreaServiceImpl implements MallPreferenceAreaService {

    @Autowired
    private MallPreferenceAreaDao preferenceAreaDao;

    @Override
    @Transactional(readOnly = true)
    public List<MallPreferenceAreaVo> listAll() {
        List<MallPreferenceArea> preferenceAreas = preferenceAreaDao.getAll(null, null);
        if (CollectionUtils.isNotEmpty(preferenceAreas)) {
            return Mapper.Default().mapArray(preferenceAreas, MallPreferenceAreaVo.class);
        }
        return null;
    }
}
