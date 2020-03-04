package me.own.learn.admin.service.impl;

import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.admin.dao.WebMenuDao;
import me.own.learn.admin.exception.WebMenuUnsetException;
import me.own.learn.admin.po.WebMenu;
import me.own.learn.admin.service.WebMenuService;
import me.own.learn.admin.vo.WebMenuVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/2 17:30
 */
@Service
public class WebMenuServiceImpl implements WebMenuService {

    @Autowired
    private WebMenuDao webMenuDao;

    @Override
    @Transactional(readOnly = true)
    public List<WebMenuVo> getByAdminId(long adminId) {

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WebMenuVo> getMenus() {
        QueryCriteriaUtil query = new QueryCriteriaUtil(WebMenu.class);
        List<WebMenu> menus = webMenuDao.getAll(null, null);
        if (CollectionUtils.isEmpty(menus)) {
            throw new WebMenuUnsetException();
        }
        return Mapper.Default().mapArray(menus, WebMenuVo.class);
    }
}
