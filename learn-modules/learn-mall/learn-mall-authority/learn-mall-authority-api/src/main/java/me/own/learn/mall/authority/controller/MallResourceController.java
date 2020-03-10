package me.own.learn.mall.authority.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.mall.aythority.service.MallResourceService;
import me.own.learn.mall.aythority.vo.MallMenuVo;
import me.own.learn.mall.aythority.vo.MallResourceVo;
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
 * @Date: 2020/3/6 14:59
 */
@RestController
@RequestMapping(value = "/api/v1/mall/resources")
@Api(tags = "MallResourceController", description = "商城管理员资源管理")
public class MallResourceController {

    @Autowired
    private MallResourceService mallResourceService;


    @ApiOperation("分页获取菜单列表")
    @RequestMapping(method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity page(HttpServletRequest request, AdminAccessToken aat,
                               @RequestParam(required = false) Long categoryId,
                               @RequestParam(required = false) String nameKeyword,
                               @RequestParam(required = false) String urlKeyword,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<MallResourceVo> result = mallResourceService.page(pageNum, pageSize, categoryId, nameKeyword, urlKeyword);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("查询所有后台资源")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity listAll(HttpServletRequest request, AdminAccessToken aat) {
        Map<String, Object> response = new HashMap<>();
        List<MallResourceVo> result = mallResourceService.listAll();
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
