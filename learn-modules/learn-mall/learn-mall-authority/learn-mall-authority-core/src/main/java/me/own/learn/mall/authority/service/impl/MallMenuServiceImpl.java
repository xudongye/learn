package me.own.learn.mall.authority.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.mall.authority.dao.MallMenuDao;
import me.own.learn.mall.authority.dao.MallRoleMenuRelationDao;
import me.own.learn.mall.authority.po.MallMenu;
import me.own.learn.mall.aythority.exception.MallMenuUnsetException;
import me.own.learn.mall.aythority.service.MallMenuService;
import me.own.learn.mall.aythority.vo.MallMenuNodeVo;
import me.own.learn.mall.aythority.vo.MallMenuVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yexudong
 * @Date: 2020/3/2 17:30
 */
@Service
public class MallMenuServiceImpl implements MallMenuService {

    @Autowired
    private MallMenuDao mallMenuDao;
    @Autowired
    private MallRoleMenuRelationDao mallRoleMenuRelationDao;

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<MallMenuVo> page(int pageNum, int pageSize, long parentId) {

        QueryCriteriaUtil query = new QueryCriteriaUtil(MallMenu.class);
        query.setSimpleCondition("parentId", parentId + "", QueryConstants.SimpleQueryMode.Equal);
        PageQueryResult<MallMenu> result = mallMenuDao.pageQuery(pageNum, pageSize, query);

        return result.mapItems(MallMenuVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MallMenuVo> getByAdminId(long adminId) {
        List<MallMenu> mallMenus = mallMenuDao.getByAdminId(adminId);
        if (CollectionUtils.isNotEmpty(mallMenus)) {
            return Mapper.Default().mapArray(mallMenus, MallMenuVo.class);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MallMenuVo> getByRoleId(long roleId) {
        List<MallMenu> mallMenus = mallRoleMenuRelationDao.getByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(mallMenus)) {
            return Mapper.Default().mapArray(mallMenus, MallMenuVo.class);
        }
        return null;
    }


    @Override
    @Transactional(readOnly = true)
    public List<MallMenuVo> getMenus() {
        List<MallMenu> menus = mallMenuDao.getAll(null, null);
        if (CollectionUtils.isEmpty(menus)) {
            throw new MallMenuUnsetException();
        }
        return Mapper.Default().mapArray(menus, MallMenuVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MallMenuNodeVo> treeList() {
        List<MallMenuVo> menuVos = this.getMenus();
        List<MallMenuNodeVo> result = menuVos.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuVos)).collect(Collectors.toList());
        return result;
    }

    private MallMenuNodeVo covertMenuNode(MallMenuVo menuVo, List<MallMenuVo> menuVos) {
        MallMenuNodeVo nodeVo = new MallMenuNodeVo();
        BeanUtils.copyProperties(menuVo, nodeVo);
        List<MallMenuNodeVo> children = menuVos.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menuVo.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuVos)).collect(Collectors.toList());
        nodeVo.setChildren(children);
        return nodeVo;
    }
}
