package me.own.learn.mall.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.mall.product.dto.ProductBrandDto;
import me.own.learn.mall.product.dto.ProductCategoryDto;
import me.own.learn.mall.product.service.ProductCategoryService;
import me.own.learn.mall.product.vo.ProductBrandVo;
import me.own.learn.mall.product.vo.ProductCategoryVo;
import me.own.learn.mall.product.vo.ProductCategoryWithChildrenItemVo;
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
 * @Date: 2020/3/4 9:58
 */
@RestController
@RequestMapping(value = "/api/v1/mall/product-category")
@Api(tags = "ProductCategoryController", description = "商品分类管理")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @ApiOperation("通过id获取品牌分类")
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity getById(HttpServletRequest request, AdminAccessToken aat,
                                  @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        ProductCategoryVo productCategoryVo = productCategoryService.getById(id);
        response.put("data", productCategoryVo);
        response.put("code", 200);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("添加品牌分类")
    @RequestMapping(method = RequestMethod.POST)
    @AdminAuthenticationRequired
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity addCategory(HttpServletRequest request, AdminAccessToken aat,
                                      @Validated @RequestBody ProductCategoryDto categoryDto) {
        Map<String, Object> response = new HashMap<>();
        ProductCategoryVo productCategoryVo = productCategoryService.create(categoryDto);
        response.put("data", productCategoryVo);
        response.put("code", 201);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("通过id更新品牌分类")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @AdminAuthenticationRequired
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity updateCategory(HttpServletRequest request, AdminAccessToken aat,
                                         @PathVariable Long id,
                                         @Validated @RequestBody ProductCategoryDto categoryDto) {
        Map<String, Object> response = new HashMap<>();
        ProductCategoryVo productCategoryVo = productCategoryService.updateById(id, categoryDto);
        response.put("data", productCategoryVo);
        response.put("code", 201);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("分页查询商品分类列表")
    @RequestMapping(value = "/{parentId}", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity page(HttpServletRequest request, AdminAccessToken aat,
                               @PathVariable Long parentId,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Map<String, Object> response = new HashMap<>();
        PageQueryResult<ProductCategoryVo> result = productCategoryService.page(pageNum, pageSize, parentId);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("查询所有一级分类及子分类")
    @RequestMapping(value = "/withCateChildren", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity withAttr(HttpServletRequest request, AdminAccessToken aat) {
        Map<String, Object> response = new HashMap<>();
        List<ProductCategoryWithChildrenItemVo> itemVos = productCategoryService.listWithChildren();
        response.put("code", 200);
        response.put("data", itemVos);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
