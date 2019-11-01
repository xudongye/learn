package me.own.learn.store.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.learn.store.product.dto.ProductPropertyDto;
import me.own.learn.store.product.service.ProductQueryCondition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/store/customer/products")
@Api(value = "会员商品接口", description = "会员商品模型查看")
public class CustomerProductController {

    @ApiOperation(value = "会员分页获取商品", tags = "product_customer")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity page(HttpServletRequest request,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               @RequestParam(required = false) ProductQueryCondition condition) {
        Map<String, Object> response = new HashMap<>();

        response.put("code", 201);
        response.put("message", "产品属性设置成功");
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
