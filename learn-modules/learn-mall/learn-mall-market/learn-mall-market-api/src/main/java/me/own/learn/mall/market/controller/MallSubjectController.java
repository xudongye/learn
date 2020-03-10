package me.own.learn.mall.market.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.mall.market.service.MallSubjectService;
import me.own.learn.mall.market.vo.MallSubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yexudong
 * @Date: 2020/3/9 17:01
 */
@RestController
@RequestMapping(value = "/api/v1/mall/subjects")
@Api(tags = "MallSubjectController", description = "商品专题管理")
public class MallSubjectController {

    @Autowired
    private MallSubjectService mallSubjectService;

    @ApiOperation("分页查询品牌列表")
    @RequestMapping(method = RequestMethod.GET)
//    @AdminAuthenticationRequired
    public ResponseEntity page(HttpServletRequest request, AdminAccessToken aat,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Map<String, Object> response = new HashMap<>();
        PageQueryResult<MallSubjectVo> result = mallSubjectService.page(pageNum, pageSize, keyword);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("获取所有主题")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
//    @AdminAuthenticationRequired
    public ResponseEntity listAll(HttpServletRequest request, AdminAccessToken aat) {

        Map<String, Object> response = new HashMap<>();
        List<MallSubjectVo> result = mallSubjectService.listAll();
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
