package me.own.learn.menu.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.menu.dao.MenuDao;
import me.own.learn.menu.dto.MenuDto;
import me.own.learn.menu.exception.MenuMatchByPermException;
import me.own.learn.menu.exception.MenuNotFoundException;
import me.own.learn.menu.po.Menu;
import me.own.learn.menu.service.MenuQueryCondition;
import me.own.learn.menu.service.MenuService;
import me.own.learn.menu.vo.MenuVo;
import me.own.learn.role.service.RoleService;
import me.own.learn.role.vo.PermissionVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author:simonye
 * @date 22:36 2019/6/1
 **/
@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RoleService roleService;

    @Override
    @Transactional
    public MenuVo create(MenuDto menuDto) {
        PermissionVo permissionVo = roleService.getPermById(menuDto.getPermissionId());
        if (permMenuExist(menuDto.getPermissionId())) {
            throw new MenuMatchByPermException();
        }
        Menu menu = Mapper.Default().map(menuDto, Menu.class);
        menu.setDeleted(false);
        menu.setCreateTime(new Date());
        menuDao.create(menu);
        LOGGER.info("create new menu {} binding with permission {}", menu.getName(), permissionVo.getName());
        return Mapper.Default().map(menu, MenuVo.class);
    }

    private boolean permMenuExist(long permId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Menu.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("permissionId", permId + "", QueryConstants.SimpleQueryMode.Equal);
        return menuDao.getCount(query) > 0;
    }


    @Override
    @Transactional
    public MenuVo update(MenuDto menuDto) {
        Menu menu = menuDao.get(menuDto.getId());
        if (menu == null || menu.getDeleted()) {
            throw new MenuNotFoundException();
        }
        if (menuDto.getIsExpend() != null) {
            menu.setIsExpend(menuDto.getIsExpend());
        }
        if (menuDto.getKeyWord() != null) {
            menu.setKeyWord(menuDto.getKeyWord());
        }
        if (menuDto.getIcon() != null) {
            menu.setIcon(menuDto.getIcon());
        }
        if (menuDto.getName() != null) {
            menu.setName(menuDto.getName());
        }
        if (menuDto.getUrl() != null) {
            menu.setUrl(menuDto.getUrl());
        }
        if (menuDto.getPermissionId() != null) {
            PermissionVo permissionVo = roleService.getPermById(menuDto.getPermissionId());
            menu.setPermissionId(menuDto.getPermissionId());
            LOGGER.info("change menu {} permission {}", menu.getName(), permissionVo.getName());
        }
        menuDao.update(menu);
        return Mapper.Default().map(menu, MenuVo.class);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Menu menu = menuDao.get(id);
        if (menu == null || menu.getDeleted()) {
            throw new MenuNotFoundException();
        }
        menu.setDeleted(true);
    }

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<MenuVo> page(int pageNum, int pageSize, MenuQueryCondition condition) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Menu.class);
        if (condition != null) {
            if (condition.getName() != null) {
                query.setSimpleCondition("name", condition.getName(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getIcon() != null) {
                query.setSimpleCondition("icon", condition.getIcon(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getUrl() != null) {
                query.setSimpleCondition("url", condition.getUrl() + "", QueryConstants.SimpleQueryMode.Equal);
            }
        }
        List<QueryOrder> orders = new ArrayList<>();
        QueryOrder order = new QueryOrder();
        order.setColumnName("createTime");
        order.setOder(QueryOrder.DESC);

        PageQueryResult<Menu> result = menuDao.pageQuery(pageNum, pageSize, query, orders);

        return result.mapItems(MenuVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuVo> getDisplayMenuByPermIds(List<Long> permIds) {
        List<Menu> menus = getDisplayMenuByPermIds(permIds, 0);

        List<MenuVo> parentMenuVo = Mapper.Default().mapArray(menus, MenuVo.class);
        for (MenuVo menuVo : parentMenuVo) {
            List<Menu> childrenMenus = getDisplayMenuByPermIds(permIds, menuVo.getId());
            //只查二级菜单
            if (CollectionUtils.isNotEmpty(childrenMenus)) {
                List<MenuVo> childrenMenuVo = Mapper.Default().mapArray(childrenMenus, MenuVo.class);
                menuVo.setChildren(childrenMenuVo);
            }
        }
        return parentMenuVo;
    }

    private List<Menu> getDisplayMenuByPermIds(List<Long> permIds, long parentId) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Menu.class);
        query.setDeletedFalseCondition();
        //父级
        query.setSimpleCondition("parentId", parentId + "", QueryConstants.SimpleQueryMode.Equal);
        List<String> strings = new ArrayList<>();
        CollectionUtils.collect(permIds,
                new Transformer() {
                    public Object transform(Object input) {
                        return String.valueOf((Long) input);
                    }
                }, strings);
        query.setComplexCondition("permissionId", strings, QueryConstants.ComplexQueryMode.In);
        List<Menu> menus = menuDao.filter(query, null, null);
        if (CollectionUtils.isEmpty(menus)) {
            return null;
        }
        return menus;
    }
}
