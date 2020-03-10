package me.own.learn.mall.member.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.mall.member.service.MallMemberLevelService;
import me.own.learn.mall.member.vo.MallMemberLevelVo;
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
 * @Date: 2020/3/9 18:05
 */
@RestController
@RequestMapping(value = "/api/v1/mall/member-levels")
@Api(tags = "MallMemberLevelController", description = "会员等级管理")
public class MallMemberLevelController {

    @Autowired
    private MallMemberLevelService levelService;

    @ApiOperation("获取所有会员等级")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listByDefaultStatus(HttpServletRequest request, AdminAccessToken aat,
                                              @RequestParam(defaultValue = "0") Integer defaultStatus) {
        Map<String, Object> response = new HashMap<>();
        List<MallMemberLevelVo> result = levelService.listByDefaultStatus(defaultStatus);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
