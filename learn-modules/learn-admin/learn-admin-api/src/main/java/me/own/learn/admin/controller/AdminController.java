package me.own.learn.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.own.learn.admin.dto.AdminDto;
import me.own.learn.admin.service.AdminQueryCondition;
import me.own.learn.admin.service.AdminService;
import me.own.learn.admin.vo.AdminVo;
import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.exception.BusinessException;
import me.own.commons.base.utils.request.RequestUtils;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.role.service.RoleService;
import me.own.learn.role.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/5/29 17:50
 */
@RestController
@RequestMapping(value = "/api/v1/admins")
@Api(value = "管理员功能模块", description = "管理员功能模块")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;

    @ApiOperation("获取管理员基本信息接口")
    @RequestMapping(value = "/mine", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity mine(HttpServletRequest request, AdminAccessToken aat) {
        Map<String, Object> response = new HashMap<>();
        AdminVo adminVo = adminService.getById(aat.getAdminId());
        decorator(adminVo);
        response.put("code", 200);
        response.put("data", adminVo);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("创建管理员")
    @RequestMapping(method = RequestMethod.POST)
    @AdminAuthenticationRequired
    public ResponseEntity create(HttpServletRequest request, AdminAccessToken aat,
                                 @RequestBody AdminDto adminDto) {

        Map<String, Object> response = new HashMap<>();
        roleService.getById(adminDto.getRoleId());
        AdminVo adminVo = adminService.create(adminDto);
        decorator(adminVo);
        response.put("code", 200);
        response.put("data", adminVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }


    @ApiOperation("分页查询管理员")
    @RequestMapping(method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity page(HttpServletRequest request, AdminAccessToken aat,
                               @RequestParam(required = false) AdminQueryCondition condition,
                               @RequestParam(value = "pageNumber", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (condition == null) {
            condition = RequestUtils.buildQueryFilter(request, AdminQueryCondition.class);
        }
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<AdminVo> adminVoPageQueryResult = adminService.page(pageNum, pageSize, condition);
        for (AdminVo adminVo : adminVoPageQueryResult.getItems()) {
            decorator(adminVo);
        }
        response.put("code", 200);
        response.put("data", adminVoPageQueryResult);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("批量删除管理员")
    @RequestMapping(method = RequestMethod.DELETE)
    @AdminAuthenticationRequired
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity batchDeleted(HttpServletRequest request, AdminAccessToken aat,
                                       @RequestParam Long[] adminIds) {
        List<Long> adminIdss = Arrays.asList(adminIds);
        adminService.batchDelete(adminIdss);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private void decorator(AdminVo adminVo) {
        String roleName = null;
        if (adminVo.getRoleId() != null) {
            try {
                RoleVo roleVo = roleService.getById(adminVo.getRoleId());
                roleName = roleVo.getName();
            } catch (BusinessException be) {
                roleName = null;
            }
        }
        adminVo.setRoleName(roleName);
    }

}
