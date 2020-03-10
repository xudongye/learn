package me.own.learn.mall.market.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.learn.mall.market.dao.HomeBrandDao;
import me.own.learn.mall.market.po.HomeBrand;
import me.own.learn.mall.market.service.HomeBrandService;
import me.own.learn.mall.market.vo.HomeBrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: yexudong
 * @Date: 2020/3/10 17:32
 */
@Service
public class HomeBrandServiceImpl implements HomeBrandService {

    @Autowired
    private HomeBrandDao brandDao;

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<HomeBrandVo> page(int pageNum, int pageSize, String brandName, Integer recommendStatus) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(HomeBrand.class);

        if (brandName != null) {
            query.setSimpleCondition("brandName", brandName, QueryConstants.SimpleQueryMode.Like);
        }
        if (recommendStatus != null) {
            query.setSimpleCondition("recommendStatus", recommendStatus + "", QueryConstants.SimpleQueryMode.Equal);
        }
        PageQueryResult<HomeBrand> result = brandDao.pageQuery(pageNum, pageSize, query);
        return result.mapItems(HomeBrandVo.class);
    }
}
