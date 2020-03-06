package me.own.learn.mall.authority.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.mall.aythority.service.MallAdminService;
import me.own.learn.mall.aythority.service.MallMenuService;
import me.own.learn.mall.aythority.service.MallRoleService;
import me.own.learn.mall.aythority.vo.MallAdminVo;
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
 * @Date: 2020/3/5 16:05
 */
@RestController
@RequestMapping(value = "/api/v1/mall/admins")
@Api(tags = "MallAdminController", description = "商城管理员管理")
public class MallAdminController {

    @Autowired
    private MallMenuService menuService;
    @Autowired
    private MallAdminService adminService;
    @Autowired
    private MallRoleService mallRoleService;

    @ApiOperation("通过id获取角色")
    @RequestMapping(value = "/withRole/{adminId}", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity getRoleByAdminId(HttpServletRequest request, AdminAccessToken aat,
                                           @PathVariable Long adminId) {

        Map<String, Object> response = new HashMap<>();
        List<MallRoleVo> result = mallRoleService.getByAdminId(adminId);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @ApiOperation("获取管理员基本信息接口")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity adminInfo(HttpServletRequest request, AdminAccessToken aat) {
        Map<String, Object> response = new HashMap<>();
        MallAdminVo adminVo = adminService.getById(aat.getAdminId());
        response.put("code", 200);
        response.put("username", adminVo.getUsername());
        response.put("roles", new String[]{"TEST"});
        response.put("menu", menuService.getByAdminId(adminVo.getId()));
        response.put("icon", adminVo.getIcon());
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @ApiOperation("分页查询品牌列表")
    @RequestMapping(method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity page(HttpServletRequest request, AdminAccessToken aat,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Map<String, Object> response = new HashMap<>();
        PageQueryResult<MallAdminVo> result = adminService.page(pageNum, pageSize, keyword);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
