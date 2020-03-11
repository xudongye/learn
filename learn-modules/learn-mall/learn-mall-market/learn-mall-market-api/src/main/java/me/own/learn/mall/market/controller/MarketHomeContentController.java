package me.own.learn.mall.market.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.learn.mall.market.service.MarketHomeContentService;
import me.own.learn.mall.market.vo.MarketHomeContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: yexudong
 * @Date: 2020/3/11 14:04
 */
@RestController
@RequestMapping(value = "/api/v1/mall/market-home")
@Api(tags = "MarketHomeContentController", description = "商品首页营销内容")
public class MarketHomeContentController {

    @Autowired
    private MarketHomeContentService homeContentService;


    @ApiOperation("分页查询品牌列表")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity homeContent(HttpServletRequest request){
        Map<String, Object> response = new HashMap<>();
        MarketHomeContent result = homeContentService.getHomeContent();
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
