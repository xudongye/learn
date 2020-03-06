package me.own.learn.mall.authority.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.mall.aythority.service.MallMenuService;
import me.own.learn.mall.aythority.vo.MallMenuVo;
import me.own.learn.mall.aythority.vo.MallRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: yexudong
 * @Date: 2020/3/5 21:01
 */
@RestController
@RequestMapping(value = "/api/v1/mall/menus")
@Api(tags = "MallMenuController", description = "商城后台菜单管理")
public class MallMenuController {

    @Autowired
    private MallMenuService mallMenuService;

    @ApiOperation("分页获取菜单列表")
    @RequestMapping(value = "/{parentId}", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity page(HttpServletRequest request, AdminAccessToken aat,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               @PathVariable(value = "parentId") Long parentId) {
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<MallMenuVo> result = mallMenuService.page(pageNum, pageSize, parentId);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
