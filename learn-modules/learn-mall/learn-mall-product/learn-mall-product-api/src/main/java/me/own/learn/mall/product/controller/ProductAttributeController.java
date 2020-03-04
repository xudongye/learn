package me.own.learn.mall.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.mall.product.dto.ProductAttributeDto;
import me.own.learn.mall.product.service.ProductAttributeService;
import me.own.learn.mall.product.vo.ProductAttrInfoVo;
import me.own.learn.mall.product.vo.ProductAttributeVo;
import me.own.learn.mall.product.vo.ProductBrandVo;
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
 * @Date: 2020/3/3 17:40
 */
@RestController
@RequestMapping(value = "/api/v1/product-attr")
@Api(tags = "ProductAttributeController", description = "商品属性管理")
public class ProductAttributeController {

    @Autowired
    private ProductAttributeService productAttributeService;

    @ApiOperation("根据分类查询属性列表或参数列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "0表示属性，1表示参数", required = true, paramType = "query", dataType = "integer")})
    @RequestMapping(value = "/{cid}", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    public ResponseEntity page(HttpServletRequest request, AdminAccessToken aat,
                               @PathVariable Long cid,
                               @RequestParam(required = false) int type,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<ProductAttributeVo> result = productAttributeService.page(cid, pageNum, pageSize, type);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("添加商品属性")
    @RequestMapping(method = RequestMethod.POST)
    @AdminAuthenticationRequired
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity create(HttpServletRequest request, AdminAccessToken aat,
                                 @RequestBody ProductAttributeDto dto) {
        Map<String, Object> response = new HashMap<>();
        ProductAttributeVo productAttributeVo = productAttributeService.create(dto);
        response.put("code", 201);
        response.put("data", productAttributeVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("根据商品分类的id获取商品属性及属性分类")
    @RequestMapping(value = "/attrInfos/{productCategoryId}", method = RequestMethod.GET)
    @AdminAuthenticationRequired
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity getAttrInfo(HttpServletRequest request, AdminAccessToken aat,
                                      @PathVariable Long productCategoryId) {
        Map<String, Object> response = new HashMap<>();
        List<ProductAttrInfoVo> infoVos = productAttributeService.getProductAttrInfos(productCategoryId);
        response.put("code", 200);
        response.put("data", infoVos);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
