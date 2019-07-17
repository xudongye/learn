package me.own.learn.menu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.menu.dto.MenuDto;
import me.own.learn.menu.service.MenuQueryCondition;
import me.own.learn.menu.service.MenuService;
import me.own.learn.menu.vo.MenuVo;
import me.own.learn.role.service.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private RoleService roleService;


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

    @ApiOperation("分页获取菜单")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity page(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               @RequestParam(required = false) MenuQueryCondition condition) {
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<MenuVo> menuVoPageQueryResult = menuService.page(pageNum, pageSize, condition);
        response.put("code", 200);
        response.put("data", menuVoPageQueryResult);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("通过角色id获取相应菜单")
    @RequestMapping(value = "/2role", method = RequestMethod.GET)
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity getMenusByRoleId(HttpServletRequest request,
                                           @RequestParam long roleId) {
        Map<String, Object> response = new HashMap<>();

        List<Long> permIds = roleService.getPermIdsByRoleId(roleId);
        if (CollectionUtils.isEmpty(permIds)) {
            response.put("success", false);
            response.put("msg", "角色未分配到权限！");
            return new ResponseEntity(response, HttpStatus.OK);
        }
        List<MenuVo> menuVos = menuService.getDisplayMenuByPermIds(permIds);
        response.put("code", 200);
        response.put("data", menuVos);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
