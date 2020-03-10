package me.own.learn.mall.market.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.market.dao.MallSubjectDao;
import me.own.learn.mall.market.po.MallSubject;
import me.own.learn.mall.market.service.MallSubjectService;
import me.own.learn.mall.market.vo.MallSubjectVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/9 16:57
 */
@Service
public class MallSubjectServiceImpl implements MallSubjectService {

    @Autowired
    private MallSubjectDao mallSubjectDao;

    @Override
    @Transactional(readOnly = true)
    public List<MallSubjectVo> listAll() {
        List<MallSubject> subjects = mallSubjectDao.getAll(null, null);
        if (CollectionUtils.isNotEmpty(subjects)) {
            return Mapper.Default().mapArray(subjects, MallSubjectVo.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<MallSubjectVo> page(int pageNum, int pageSize, String keyword) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(MallSubject.class);
        if (keyword != null) {
            query.setSimpleCondition("title", keyword, QueryConstants.SimpleQueryMode.Like);
        }
        PageQueryResult<MallSubject> result = mallSubjectDao.pageQuery(pageNum, pageSize, query);
        return result.mapItems(MallSubjectVo.class);
    }
}
