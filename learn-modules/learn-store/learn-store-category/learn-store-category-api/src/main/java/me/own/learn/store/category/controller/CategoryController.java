package me.own.learn.store.category.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.utils.request.RequestUtils;
import me.own.learn.store.category.dto.CategoryDto;
import me.own.learn.store.category.service.CategoryQueryCondition;
import me.own.learn.store.category.service.CategoryService;
import me.own.learn.store.category.vo.CategoryVo;
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
 * @date 2019/6/12 15:42
 */
@RestController
@RequestMapping(value = "/api/learn/v1/categories")
@Api(value = "商城类目功能模块", description = "商城类目")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("新建类目")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createCategory(HttpServletRequest request,
                                         @RequestBody CategoryDto categoryDto) {
        Map<String, Object> response = new HashMap<>();
        CategoryVo categoryVo = categoryService.create(categoryDto);
        response.put("code", 200);
        response.put("data", categoryVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("更新类目-名称，排序")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateCategory(HttpServletRequest request,
                                         @RequestBody CategoryDto categoryDto) {
        Map<String, Object> response = new HashMap<>();
        CategoryVo categoryVo = categoryService.update(categoryDto);
        response.put("code", 200);
        response.put("data", categoryVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("分页查询类目")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity pageRole(HttpServletRequest request,
                                   @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                   @RequestParam(required = false) CategoryQueryCondition condition) {

        if (condition == null) {
            condition = RequestUtils.buildQueryFilter(request, CategoryQueryCondition.class);
        }
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<CategoryVo> categoryVoPageQueryResult = categoryService.page(pageNum, pageSize, condition);
        response.put("code", 200);
        response.put("data", categoryVoPageQueryResult);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("获取类目")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity listCategory(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        List<CategoryVo> categoryVoList = categoryService.listAll();
        response.put("code", 200);
        response.put("data", categoryVoList);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }


}