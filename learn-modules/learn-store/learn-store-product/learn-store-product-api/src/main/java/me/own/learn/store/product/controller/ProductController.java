package me.own.learn.store.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.utils.request.RequestUtils;
import me.own.learn.store.product.constant.ProductConstant;
import me.own.learn.store.product.dto.ProductDto;
import me.own.learn.store.product.service.ProductQueryCondition;
import me.own.learn.store.product.service.ProductService;
import me.own.learn.store.product.vo.ProductDetailVo;
import me.own.learn.store.product.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/7/4 14:38
 */
@RestController
@RequestMapping(value = "/api/v1/store/products")
@Api(value = "产品模型模块", description = "产品模型以及属性管理模块")
public class ProductController {

    @Autowired
    private ProductService productService;


    @ApiOperation("添加产品")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createProduct(HttpServletRequest request,
                                        @RequestBody ProductDto productDto) {
        Map<String, Object> response = new HashMap<>();
        ProductVo productVo = productService.create(productDto);
        response.put("code", 200);
        response.put("data", productVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("删除产品")
    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(HttpServletRequest request,
                                     @PathVariable Long productId) {

        productService.delete(productId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("更新产品信息")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity update(HttpServletRequest request,
                                 @RequestBody ProductDto productDto) {
        Map<String, Object> response = new HashMap<>();
        ProductVo productVo = productService.update(productDto);
        response.put("code", 200);
        response.put("data", productVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("获取产品详细信息")
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity getById(HttpServletRequest request,
                                  @PathVariable Long productId) {
        Map<String, Object> response = new HashMap<>();
        ProductDetailVo detailVo = productService.getByProductId(productId);
        response.put("code", 200);
        response.put("data", detailVo);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("分页查询产品信息")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity page(HttpServletRequest request,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               @RequestParam(required = false) ProductQueryCondition condition
    ) {
        if (condition == null) {
            condition = RequestUtils.buildQueryFilter(request, ProductQueryCondition.class);
        }
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<ProductVo> productVoPageQueryResult = productService.page(pageNum, pageSize, condition);
        response.put("code", 200);
        response.put("data", productVoPageQueryResult);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
