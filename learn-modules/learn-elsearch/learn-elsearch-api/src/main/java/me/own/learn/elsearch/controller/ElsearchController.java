package me.own.learn.elsearch.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.learn.elsearch.bo.ProductDocBo;
import me.own.learn.elsearch.service.ElsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/elsearch")
@Api(value = "搜索引擎", description = "learn项目搜索引擎模块接口")
public class ElsearchController {


    @Autowired
    private ElsearchService elsearchService;

    @ApiOperation("商品关键词搜索")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity onSearchProduct(HttpServletRequest request,
                                          @RequestParam(defaultValue = "1", name = "pageNum") int pageNum,
                                          @RequestParam(defaultValue = "10", name = "pageSize") int pageSize,
                                          @RequestParam String keyword) {
        Map<String, Object> response = new HashMap<>();
        Page<ProductDocBo> productDocBos = elsearchService.getProductByName(keyword, pageNum, pageSize);
        response.put("code", 200);
        response.put("data", productDocBos);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
