package me.own.learn.mall.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.utils.request.RequestUtils;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.mall.product.dto.NewProductDto;
import me.own.learn.mall.product.dto.ProductDto;
import me.own.learn.mall.product.service.NewProductQueryCondition;
import me.own.learn.mall.product.service.NewProductService;
import me.own.learn.mall.product.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: yexudong
 * @Date: 2020/3/5 13:46
 */
@RestController
@RequestMapping(value = "/api/v1/mall/new-products")
@Api(tags = "ProductNewController", description = "商品管理", value = "商品实体管理模块")
public class ProductNewController {

    @Autowired
    private NewProductService newProductService;


    @ApiOperation("分页查询商品列表")
    @RequestMapping(method = RequestMethod.GET)
//    @AdminAuthenticationRequired
    public ResponseEntity page(HttpServletRequest request, AdminAccessToken aat,
                               @RequestParam(required = false) NewProductQueryCondition condition,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        Map<String, Object> response = new HashMap<>();
        if (condition == null) {
            condition = RequestUtils.buildQueryFilter(request, NewProductQueryCondition.class);
        }
        PageQueryResult<ProductVo> result = newProductService.page(pageNum, pageSize, condition);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("新建商品")
    @RequestMapping(method = RequestMethod.POST)
    @AdminAuthenticationRequired
    public ResponseEntity create(HttpServletRequest request, AdminAccessToken aat,
                                 @RequestBody NewProductDto dto) {
        Map<String, Object> response = new HashMap<>();
        ProductVo productVo = newProductService.create(dto);
        response.put("code", 201);
        response.put("data", productVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
