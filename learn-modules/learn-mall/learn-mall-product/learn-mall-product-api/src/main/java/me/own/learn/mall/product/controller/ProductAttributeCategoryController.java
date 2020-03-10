package me.own.learn.mall.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.mall.product.dto.ProductBrandDto;
import me.own.learn.mall.product.service.ProductAttributeCategoryService;
import me.own.learn.mall.product.vo.ProductAttributeCategoryItemVo;
import me.own.learn.mall.product.vo.ProductAttributeCategoryVo;
import me.own.learn.mall.product.vo.ProductBrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yexudong
 * @Date: 2020/3/3 17:16
 */
@RestController
@RequestMapping(value = "/api/v1/mall/product-attr-cate")
@Api(tags = "ProductAttributeCategoryController", description = "商品属性分类")
public class ProductAttributeCategoryController {

    @Autowired
    private ProductAttributeCategoryService productAttributeCategoryService;

    @ApiOperation("添加商品属性分类")
    @RequestMapping(method = RequestMethod.POST)
    @AdminAuthenticationRequired
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity addAttrCate(HttpServletRequest request, AdminAccessToken aat,
                                      @RequestParam String name) {
        Map<String, Object> response = new HashMap<>();
        ProductAttributeCategoryVo productAttributeCategoryVo = productAttributeCategoryService.create(name);
        response.put("data", productAttributeCategoryVo);
        response.put("code", 201);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("分页查询商品属性分类")
    @RequestMapping(method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity page(HttpServletRequest request, AdminAccessToken aat,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Map<String, Object> response = new HashMap<>();
        PageQueryResult<ProductAttributeCategoryVo> result = productAttributeCategoryService.page(pageNum, pageSize);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("获取所有商品属性分类及其下属性")
    @RequestMapping(value = "/withAttr", method = RequestMethod.GET)
//    @AdminAuthenticationRequired
    public ResponseEntity withAttr(HttpServletRequest request, AdminAccessToken aat) {
        Map<String, Object> response = new HashMap<>();
        List<ProductAttributeCategoryItemVo> itemVos = productAttributeCategoryService.withAttr();
        response.put("code", 200);
        response.put("data", itemVos);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
