package me.own.learn.mall.authority.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.mall.aythority.service.MallResourceCategoryService;
import me.own.learn.mall.aythority.vo.MallResourceCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yexudong
 * @Date: 2020/3/6 15:12
 */
@RestController
@RequestMapping(value = "/api/v1/mall/resources-cate")
@Api(tags = "MallResourceCategoryController", description = "后台资源分类管理")
public class MallResourceCategoryController {

    @Autowired
    private MallResourceCategoryService mallResourceCategoryService;

    @ApiOperation("查询所有后台资源分类")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity listAll(HttpServletRequest request, AdminAccessToken aat) {
        Map<String, Object> response = new HashMap<>();
        List<MallResourceCategoryVo> categoryVos = mallResourceCategoryService.listAll();
        response.put("code", 200);
        response.put("data", categoryVos);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
