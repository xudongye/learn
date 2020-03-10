package me.own.learn.mall.market.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.mall.market.service.MallPreferenceAreaService;
import me.own.learn.mall.market.vo.MallPreferenceAreaVo;
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
 * @Date: 2020/3/9 17:34
 */
@RestController
@RequestMapping(value = "/api/v1/mall/preference-areas")
@Api(tags = "MallPreferenceAreaController", description = "商品优选专区管理")
public class MallPreferenceAreaController {

    @Autowired
    private MallPreferenceAreaService preferenceAreaService;

    @ApiOperation("获取所有主题")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
//    @AdminAuthenticationRequired
    public ResponseEntity listAll(HttpServletRequest request, AdminAccessToken aat) {

        Map<String, Object> response = new HashMap<>();
        List<MallPreferenceAreaVo> result = preferenceAreaService.listAll();
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
