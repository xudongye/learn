package me.own.learn.mall.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.mall.product.dto.ProductBrandDto;
import me.own.learn.mall.product.service.ProductBrandService;
import me.own.learn.mall.product.vo.ProductBrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: yexudong
 * @Date: 2020/3/3 11:39
 */
@RestController
@RequestMapping(value = "/api/v1/mall/product-brands")
@Api(tags = "ProductBrandController", description = "商品品牌管理")
public class ProductBrandController {

    @Autowired
    private ProductBrandService productBrandService;


    @ApiOperation("添加品牌")
    @RequestMapping(method = RequestMethod.POST)
    @AdminAuthenticationRequired
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity addBrand(HttpServletRequest request, AdminAccessToken aat,
                                   @Validated @RequestBody ProductBrandDto brandDto) {
        Map<String, Object> response = new HashMap<>();
        ProductBrandVo productBrandVo = productBrandService.create(brandDto);
        response.put("data", productBrandVo);
        response.put("code", 201);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("通过id获取品牌信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity getById(HttpServletRequest request, AdminAccessToken aat,
                                  @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        ProductBrandVo productBrandVo = productBrandService.getById(id);
        response.put("data", productBrandVo);
        response.put("code", 200);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("通过id更新品牌信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @AdminAuthenticationRequired
    public ResponseEntity updateById(HttpServletRequest request, AdminAccessToken aat,
                                     @PathVariable Long id,
                                     @Validated @RequestBody ProductBrandDto brandDto) {
        Map<String, Object> response = new HashMap<>();
        ProductBrandVo productBrandVo = productBrandService.updateById(id, brandDto);
        response.put("data", productBrandVo);
        response.put("code", 201);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("分页查询品牌列表")
    @RequestMapping(method = RequestMethod.GET)
//    @AdminAuthenticationRequired
    public ResponseEntity page(HttpServletRequest request, AdminAccessToken aat,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Map<String, Object> response = new HashMap<>();
        PageQueryResult<ProductBrandVo> result = productBrandService.page(pageNum, pageSize, keyword);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
