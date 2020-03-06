package me.own.learn.mall.authority.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.authority.dao.MallResourceDao;
import me.own.learn.mall.authority.dao.MallRoleResourceRelationDao;
import me.own.learn.mall.authority.po.MallResource;
import me.own.learn.mall.aythority.service.MallResourceService;
import me.own.learn.mall.aythority.vo.MallResourceVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/6 11:36
 */
@Service
public class MallResourceServiceImpl implements MallResourceService {

    @Autowired
    private MallRoleResourceRelationDao mallRoleResourceRelationDao;
    @Autowired
    private MallResourceDao mallResourceDao;

    @Override
    @Transactional(readOnly = true)
    public List<MallResourceVo> getByRoleId(long roleId) {
        List<MallResource> resources = mallRoleResourceRelationDao.getByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(resources)) {
            return Mapper.Default().mapArray(resources, MallResourceVo.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<MallResourceVo> page(int pageNum, int pageSize, Long categoryId, String nameKw, String urlKw) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(MallResource.class);
        if (categoryId != null) {
            query.setSimpleCondition("categoryId", categoryId + "", QueryConstants.SimpleQueryMode.Equal);
        }
        if (nameKw != null) {
            query.setSimpleCondition("name", nameKw, QueryConstants.SimpleQueryMode.Like);
        }
        if (urlKw != null) {
            query.setSimpleCondition("url", urlKw, QueryConstants.SimpleQueryMode.Like);
        }
        PageQueryResult<MallResource> result = mallResourceDao.pageQuery(pageNum, pageSize, query);
        return result.mapItems(MallResourceVo.class);
    }
}
