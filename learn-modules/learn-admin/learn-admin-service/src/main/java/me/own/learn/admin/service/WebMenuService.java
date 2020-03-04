package me.own.learn.admin.service;

import me.own.learn.admin.vo.WebMenuVo;

import java.util.List;

/**
 * @author: yexudong
 * @Date: 2020/3/2 17:24
 */
public interface WebMenuService {

    List<WebMenuVo> getByAdminId(long adminId);

    List<WebMenuVo> getMenus();
}
