package me.own.learn.mall.authority.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.mall.aythority.service.MallMenuService;
import me.own.learn.mall.aythority.service.MallResourceService;
import me.own.learn.mall.aythority.service.MallRoleService;
import me.own.learn.mall.aythority.vo.MallAdminVo;
import me.own.learn.mall.aythority.vo.MallMenuVo;
import me.own.learn.mall.aythority.vo.MallResourceVo;
import me.own.learn.mall.aythority.vo.MallRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yexudong
 * @Date: 2020/3/5 16:41
 */
@RestController
@RequestMapping(value = "/api/v1/mall/roles")
@Api(tags = "MallRoleController", description = "商城管理员角色管理")
public class MallRoleController {

    @Autowired
    private MallRoleService mallRoleService;
    @Autowired
    private MallResourceService mallResourceService;
    @Autowired
    private MallMenuService mallMenuService;

    @ApiOperation("分页获取角色列表")
    @RequestMapping(method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity page(HttpServletRequest request, AdminAccessToken aat,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               @RequestParam(value = "keyword", required = false) String keyword) {
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<MallRoleVo> result = mallRoleService.page(pageNum, pageSize, keyword);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity listAllRole(HttpServletRequest request, AdminAccessToken aat) {

        Map<String, Object> response = new HashMap<>();
        List<MallRoleVo> result = mallRoleService.listAll();
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @ApiOperation("通过角色id获取资源")
    @RequestMapping(value = "/withResource/{roleId}", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity getResourceByRoleId(HttpServletRequest request, AdminAccessToken aat,
                                              @PathVariable Long roleId) {

        Map<String, Object> response = new HashMap<>();
        List<MallResourceVo> result = mallResourceService.getByRoleId(roleId);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("通过角色id获取菜单")
    @RequestMapping(value = "/withMenu/{roleId}", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity getMenuByRoleId(HttpServletRequest request, AdminAccessToken aat,
                                          @PathVariable Long roleId) {

        Map<String, Object> response = new HashMap<>();
        List<MallMenuVo> result = mallMenuService.getByRoleId(roleId);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
