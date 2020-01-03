package me.own.learn.store.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.learn.store.product.dto.ProductPropertyDto;
import me.own.learn.store.product.service.ProductQueryCondition;
import me.own.learn.store.product.service.ProductService;
import me.own.learn.store.product.service.SearchProductCondition;
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

@RestController
@RequestMapping(value = "/api/v1/store/customer/products")
@Api(value = "会员商品接口", description = "会员商品模型查看")
public class CustomerProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "会员分页获取商品", tags = "product_customer")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity page(HttpServletRequest request,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               @RequestParam(required = false) SearchProductCondition condition) {
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<ProductVo> result = productService.searchProduct(pageNum, pageSize, condition);
        response.put("code", 200);
        response.put("data", result);
        response.put("message", "success");
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "会员获取商品详情", tags = "product_customer")
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity productDetail(HttpServletRequest request,
                                        @PathVariable Long productId) {

        Map<String, Object> response = new HashMap<>();
        ProductDetailVo detailVo = productService.getByProductId(productId);
        response.put("code", 200);
        response.put("data", detailVo);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
