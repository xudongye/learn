package me.own.learn.mall.authority.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.authority.dao.MallAdminRoleRelationDao;
import me.own.learn.mall.authority.dao.MallRoleDao;
import me.own.learn.mall.authority.po.MallRole;
import me.own.learn.mall.aythority.exception.MallRoleNotFoundException;
import me.own.learn.mall.aythority.service.MallRoleService;
import me.own.learn.mall.aythority.vo.MallRoleVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/5 16:37
 */
@Service
public class MallRoleServiceImpl implements MallRoleService {

    @Autowired
    private MallRoleDao mallRoleDao;
    @Autowired
    private MallAdminRoleRelationDao mallAdminRoleRelationDao;

    @Override
    @Transactional(readOnly = true)
    public List<MallRoleVo> listAll() {
        List<MallRole> mallRoles = mallRoleDao.getAll(null, null);
        if (CollectionUtils.isNotEmpty(mallRoles)) {
            return Mapper.Default().mapArray(mallRoles, MallRoleVo.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MallRoleVo> getByAdminId(long id) {
        List<MallRole> roles = mallAdminRoleRelationDao.getByAdminId(id);
        if (CollectionUtils.isNotEmpty(roles)) {
            return Mapper.Default().mapArray(roles, MallRoleVo.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<MallRoleVo> page(int pageNum, int pageSize, String keyword) {

        QueryCriteriaUtil query = new QueryCriteriaUtil(MallRole.class);
        if (keyword != null) {
            query.setSimpleCondition("name", keyword, QueryConstants.SimpleQueryMode.Like);
        }
        PageQueryResult<MallRole> result = mallRoleDao.pageQuery(pageNum, pageSize, query);

        return result.mapItems(MallRoleVo.class);
    }
}
