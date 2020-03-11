package me.own.learn.mall.market.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.utils.request.RequestUtils;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.mall.market.service.*;
import me.own.learn.mall.market.vo.*;
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
 * @Date: 2020/3/10 15:38
 */
@RestController
@RequestMapping(value = "/api/v1/mall")
@Api(tags = "HomeManageController", description = "商城首页内容管理")
public class HomeManageController {

    @Autowired
    private HomeAdvertiseService advertiseService;
    @Autowired
    private HomeRecommendSubjectService subjectService;
    @Autowired
    private HomeRecommendProductService hotProductService;
    @Autowired
    private HomeRecommendNewProductService newProductService;
    @Autowired
    private HomeBrandService brandService;
    @Autowired
    private HomeFlashPromotionService promotionService;

    @ApiOperation("分页查询首页广告列表")
    @RequestMapping(value = "/home-advertises", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity pageAdv(HttpServletRequest request, AdminAccessToken aat,
                                  @RequestParam(required = false) HomeAdvertiseQueryCondition condition,
                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (condition == null) {
            condition = RequestUtils.buildQueryFilter(request, HomeAdvertiseQueryCondition.class);
        }
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<HomeAdvertiseVo> result = advertiseService.page(pageNum, pageSize, condition);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("通过id查询广告详情")
    @RequestMapping(value = "/home-advertises/{id}", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity getById(HttpServletRequest request, AdminAccessToken aat,
                                  @PathVariable long id) {
        Map<String, Object> response = new HashMap<>();
        HomeAdvertiseVo result = advertiseService.getById(id);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("分页查询首页专题推荐列表")
    @RequestMapping(value = "/home-recommend-subjects", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity pageSubj(HttpServletRequest request, AdminAccessToken aat,
                                   @RequestParam(value = "subjectName", required = false) String subjectName,
                                   @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                   @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Map<String, Object> response = new HashMap<>();
        PageQueryResult<HomeRecommendSubjectVo> result = subjectService.page(pageNum, pageSize, subjectName, recommendStatus);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("分页查询首页热门商品列表")
    @RequestMapping(value = "/home-hot-products", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity pageProd(HttpServletRequest request, AdminAccessToken aat,
                                   @RequestParam(value = "productName", required = false) String productName,
                                   @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                   @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Map<String, Object> response = new HashMap<>();
        PageQueryResult<HomeRecommendProductVo> result = hotProductService.page(pageNum, pageSize, productName, recommendStatus);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("分页查询首页新商品列表")
    @RequestMapping(value = "/home-new-products", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity pageNewProd(HttpServletRequest request, AdminAccessToken aat,
                                      @RequestParam(value = "productName", required = false) String productName,
                                      @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                      @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Map<String, Object> response = new HashMap<>();
        PageQueryResult<HomeRecommendNewProductVo> result = newProductService.page(pageNum, pageSize, productName, recommendStatus);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("分页查询首页品牌列表")
    @RequestMapping(value = "/home-brands", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity pageBrand(HttpServletRequest request, AdminAccessToken aat,
                                    @RequestParam(value = "brandName", required = false) String brandName,
                                    @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                    @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Map<String, Object> response = new HashMap<>();
        PageQueryResult<HomeBrandVo> result = brandService.page(pageNum, pageSize, brandName, recommendStatus);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("分页查询首页秒杀活动列表")
    @RequestMapping(value = "/home-flash-promotions", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity pageFlashPromotion(HttpServletRequest request, AdminAccessToken aat,
                                             @RequestParam(value = "keyword", required = false) String keyword,
                                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Map<String, Object> response = new HashMap<>();
        PageQueryResult<HomeFlashPromotionVo> result = promotionService.page(pageNum, pageSize, keyword);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("首页秒杀活动列表时间段")
    @RequestMapping(value = "/home-flash-promotion-sessions", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity listFlashSession(HttpServletRequest request, AdminAccessToken aat) {

        Map<String, Object> response = new HashMap<>();
        List<HomeFlashPromotionSessionVo> result = promotionService.listAllSession();
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("获取全部可选场次及其商品数量")
    @RequestMapping(value = "/home-flash-promotion-sessions-select", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity listSelectedFlashSession(HttpServletRequest request, AdminAccessToken aat,
                                                   @RequestParam Long flashPromotionId) {

        Map<String, Object> response = new HashMap<>();
        List<HomeFlashPromotionSessionVo> result = promotionService.listByPromotionId(flashPromotionId);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("分页获取全部场次及其商品信息")
    @RequestMapping(value = "/home-flash-promotion-sessions-product", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity listSelectedFlashSession(HttpServletRequest request, AdminAccessToken aat,
                                                   @RequestParam(value = "flashPromotionId") Long flashPromotionId,
                                                   @RequestParam(value = "flashPromotionSessionId") Long flashPromotionSessionId,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        Map<String, Object> response = new HashMap<>();
        PageQueryResult<HomeFlashProductVo> result = promotionService.pageFlashProductInfo(pageNum,pageSize,flashPromotionId,flashPromotionSessionId);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
