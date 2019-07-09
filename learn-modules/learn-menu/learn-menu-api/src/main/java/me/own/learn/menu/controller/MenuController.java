package me.own.learn.menu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.learn.menu.dto.MenuDto;
import me.own.learn.menu.service.MenuService;
import me.own.learn.menu.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:simonye
 * @date 23:04 2019/6/1
 **/
@RestController
@RequestMapping(value = "/api/v1/menus")
@Api(value = "管理员菜单功能模块", description = "菜单功能模块")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("新建菜单")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(HttpServletRequest request,
                                 @RequestBody MenuDto menuDto) {
        Map<String, Object> response = new HashMap<>();
        MenuVo menuVo = menuService.create(menuDto);
        response.put("code", 200);
        response.put("data", menuVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }



}
