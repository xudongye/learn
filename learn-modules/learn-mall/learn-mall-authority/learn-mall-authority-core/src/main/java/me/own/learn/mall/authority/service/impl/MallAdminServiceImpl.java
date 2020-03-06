package me.own.learn.mall.authority.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.authority.dao.MallAdminDao;
import me.own.learn.mall.authority.po.MallAdmin;
import me.own.learn.mall.aythority.exception.MallAdminNotFoundException;
import me.own.learn.mall.aythority.service.MallAdminService;
import me.own.learn.mall.aythority.vo.MallAdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: yexudong
 * @Date: 2020/3/5 16:02
 */
@Service
public class MallAdminServiceImpl implements MallAdminService {

    @Autowired
    private MallAdminDao mallAdminDao;

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<MallAdminVo> page(int pageNum, int pageSize, String keyword) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(MallAdmin.class);
        if (keyword != null) {
            query.setSimpleCondition("username", keyword, QueryConstants.SimpleQueryMode.Like);
        }
        PageQueryResult<MallAdmin> result = mallAdminDao.pageQuery(pageNum, pageSize, query);

        return result.mapItems(MallAdminVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public MallAdminVo getById(long id) {
        MallAdmin mallAdmin = mallAdminDao.get(id);
        if (mallAdmin == null) {
            throw new MallAdminNotFoundException();
        }
        return Mapper.Default().map(mallAdmin, MallAdminVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public MallAdminVo getByUsername(String username) {

        return null;
    }
}
