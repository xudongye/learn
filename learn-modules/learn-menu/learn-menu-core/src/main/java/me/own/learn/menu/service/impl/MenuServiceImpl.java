package me.own.learn.menu.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.menu.dao.MenuDao;
import me.own.learn.menu.dto.MenuDto;
import me.own.learn.menu.exception.MenuNotFoundException;
import me.own.learn.menu.po.Menu;
import me.own.learn.menu.service.MenuQueryCondition;
import me.own.learn.menu.service.MenuService;
import me.own.learn.menu.vo.MenuVo;
import me.own.learn.role.service.RoleService;
import me.own.learn.role.vo.PermissionVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Double sequence = selectMaxSequence(menuDto.getParent());

        Menu menu = Mapper.Default().map(menuDto, Menu.class);
        if (menuDto.getParent() != null) {
            Menu parent = menuDao.get(menuDto.getParent());
            menu.setParent(parent);
        }
        menu.setSequence(sequence);
        menu.setDeleted(false);
        menu.setCreateTime(new Date());
        menuDao.create(menu);
        LOGGER.info("create new menu {} binding with permission {}", menu.getName(), permissionVo.getName());
        return Mapper.Default().map(menu, MenuVo.class);
    }

    private double selectMaxSequence(Long parent) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Menu.class);
        double addSequence = 0.1;
        if (parent != null) {
            addSequence = 1.0;
            query.setSimpleCondition("parent.id", parent + "", QueryConstants.SimpleQueryMode.Equal);
        }
        List<Menu> menus = menuDao.filter(query, null, new QueryOrder("sequence", QueryOrder.ASC));
        if (CollectionUtils.isNotEmpty(menus)) {
            return menus.get(0).getSequence() + addSequence;
        }
        return 0.0;
    }

    @Override
    @Transactional
    public MenuVo update(MenuDto menuDto) {
        Menu menu = menuDao.get(menuDto.getId());
        if (menu == null || menu.getDeleted()) {
            throw new MenuNotFoundException();
        }
        if (menuDto.getComponent() != null) {
            menu.setComponent(menuDto.getComponent());
        }
        if (menuDto.getIcon() != null) {
            menu.setIcon(menuDto.getIcon());
        }
        if (menuDto.getName() != null) {
            menu.setName(menuDto.getName());
        }
        if (menuDto.getPath() != null) {
            menu.setPath(menuDto.getPath());
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
            if (condition.getComponent() != null) {
                query.setSimpleCondition("component", condition.getComponent(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getName() != null) {
                query.setSimpleCondition("name", condition.getName(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getPath() != null) {
                query.setSimpleCondition("path", condition.getPath(), QueryConstants.SimpleQueryMode.Like);
            }
            if (condition.getSequence() != null) {
                query.setSimpleCondition("sequence", condition.getSequence() + "", QueryConstants.SimpleQueryMode.Equal);
            }
        }
        List<QueryOrder> orders = new ArrayList<>();
        QueryOrder order = new QueryOrder();
        order.setColumnName("sequence");
        order.setOder(QueryOrder.ASC);

        PageQueryResult<Menu> result = menuDao.pageQuery(pageNum, pageSize, query, orders);

        return result.mapItems(MenuVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuVo> getAllDisplayMenu() {

        return null;
    }
}
