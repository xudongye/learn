package me.own.learn.menu.service;

import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.menu.dto.MenuDto;
import me.own.learn.menu.vo.MenuVo;

import java.util.List;

/**
 * @author:simonye
 * @date 22:28 2019/6/1
 **/
public interface MenuService {

    MenuVo create(MenuDto menuDto);

    MenuVo update(MenuDto menuDto);

    void delete(long id);

    PageQueryResult<MenuVo> page(int pageNum, int pageSize, MenuQueryCondition condition);

    List<MenuVo> getAllDisplayMenu();
}
