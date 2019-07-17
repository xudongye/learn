package me.own.learn.role.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.utils.enums.EnumUtil;
import me.own.commons.base.utils.request.RequestUtils;
import me.own.learn.role.constant.RoleConstant;
import me.own.learn.role.dto.PermissionDto;
import me.own.learn.role.dto.RoleDto;
import me.own.learn.role.service.RoleQueryCondition;
import me.own.learn.role.service.RoleService;
import me.own.learn.role.vo.PermissionVo;
import me.own.learn.role.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/5/29 16:45
 */
@RestController
@RequestMapping(value = "/api/v1/roles")
@Api(value = "管理员角色功能模块", description = "角色功能模块")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("新建角色")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createRole(HttpServletRequest request,
                                     @RequestBody RoleDto roleDto) {
        Map<String, Object> response = new HashMap<>();
        RoleVo roleVo = roleService.create(roleDto);
        response.put("code", 200);
        response.put("data", roleVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("角色授权")
    @RequestMapping(value = "/permissions-given", method = RequestMethod.POST)
    @AdminAuthenticationRequired
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity givePerms(HttpServletRequest request, AdminAccessToken aat,
                                    @RequestParam Long roleId,
                                    @RequestParam Long[] permIds) {
        Map<String, Object> response = new HashMap<>();
        RoleVo roleVo = roleService.givePerm(roleId, permIds);
        response.put("code", 200);
        response.put("data", roleVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("新增权限")
    @RequestMapping(value = "/permissions", method = RequestMethod.POST)
    public ResponseEntity createPerm(HttpServletRequest request,
                                     @RequestBody PermissionDto permissionDto) {
        Map<String, Object> response = new HashMap<>();
        PermissionVo permissionVo = roleService.create(permissionDto);
        response.put("code", 200);
        response.put("data", permissionVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("分页查询角色")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity pageRole(HttpServletRequest request,
                                   @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                   @RequestParam(required = false) RoleQueryCondition condition) {

        if (condition == null) {
            condition = RequestUtils.buildQueryFilter(request, RoleQueryCondition.class);
        }
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<RoleVo> roleVoPageQueryResult = roleService.page(pageNum, pageSize, condition);
        response.put("code", 200);
        response.put("data", roleVoPageQueryResult);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("角色静态常量查询")
    @RequestMapping(value = "/constants", method = RequestMethod.GET)
    public ResponseEntity constant(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> roleLevels = EnumUtil.getEnumNameValueList(RoleConstant.RoleLevel.class);
        response.put("code", 200);
        response.put("roleLevels", roleLevels);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
