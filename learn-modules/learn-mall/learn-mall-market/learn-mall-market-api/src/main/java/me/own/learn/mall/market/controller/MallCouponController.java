package me.own.learn.mall.market.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.mall.market.service.MallCouponService;
import me.own.learn.mall.market.vo.MallCouponHistoryVo;
import me.own.learn.mall.market.vo.MallCouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: yexudong
 * @Date: 2020/3/13 14:54
 */
@RestController
@RequestMapping(value = "/api/v1/mall/coupons")
@Api(tags = "MallCouponController", description = "商城优惠券管理")
public class MallCouponController {

    @Autowired
    private MallCouponService couponService;

    @ApiOperation("分页查询商城优惠券")
    @RequestMapping(method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity page(HttpServletRequest request, AdminAccessToken aat,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "type", required = false) Integer type,
                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<MallCouponVo> result = couponService.page(pageNum, pageSize, name, type);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("分页查询商城优惠券使用记录")
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity pageHistory(HttpServletRequest request, AdminAccessToken aat,
                                      @RequestParam(value = "couponId", required = false) Long couponId,
                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<MallCouponHistoryVo> result = couponService.pageByCouponId(pageNum, pageSize, couponId);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("通过id获取商城优惠券详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity getById(HttpServletRequest request, AdminAccessToken aat,
                                  @PathVariable(value = "id") Long id) {
        Map<String, Object> response = new HashMap<>();
        MallCouponVo couponVo = couponService.getById(id);
        response.put("code", 200);
        response.put("data", couponVo);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
