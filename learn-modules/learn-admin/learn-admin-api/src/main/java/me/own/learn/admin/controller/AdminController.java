package me.own.learn.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.learn.admin.dto.AdminDto;
import me.own.learn.admin.service.AdminQueryCondition;
import me.own.learn.admin.service.AdminService;
import me.own.learn.admin.vo.AdminVo;
import me.own.learn.commons.base.dao.PageQueryResult;
import me.own.learn.commons.base.exception.BusinessException;
import me.own.learn.role.service.RoleService;
import me.own.learn.role.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/5/29 17:50
 */
@RestController
@RequestMapping(value = "/api/learn/v1/admins")
@Api(tags = "管理员功能模块", description = "管理员功能模块")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;

    @ApiOperation("创建管理员")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(HttpServletRequest request,
                                 @RequestBody AdminDto adminDto) {

        Map<String, Object> response = new HashMap<>();
        roleService.getById(adminDto.getRoleId());
        AdminVo adminVo = adminService.create(adminDto);
        response.put("code", 200);
        response.put("data", adminVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }


    @ApiOperation("分页查询管理员")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity page(HttpServletRequest request,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               @RequestBody(required = false) AdminQueryCondition condition) {

        Map<String, Object> response = new HashMap<>();
        PageQueryResult<AdminVo> adminVoPageQueryResult = adminService.page(pageNum, pageSize, condition);
        for (AdminVo adminVo : adminVoPageQueryResult.getItems()) {
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
        response.put("code", 200);
        response.put("data", adminVoPageQueryResult);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

}
