package me.own.learn.customer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.utils.request.RequestUtils;
import me.own.learn.customer.service.CustomerQueryCondition;
import me.own.learn.customer.service.CustomerService;
import me.own.learn.customer.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/6/17 15:20
 */
@RestController
@RequestMapping(value = "/api/v1/customers")
@Api(value = "会员功能模块", description = "会员管理功能模块")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation("分页查询会员")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity page(HttpServletRequest request,
                               @RequestParam(value = "pageNumber", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               @RequestParam(required = false) CustomerQueryCondition condition) {
        if (condition == null) {
            condition = RequestUtils.buildQueryFilter(request, CustomerQueryCondition.class);
        }
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<CustomerVo> result = customerService.page(pageNum, pageSize, condition);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("获取会员详情")
    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public ResponseEntity getById(HttpServletRequest request,
                                  @PathVariable Long customerId) {
        Map<String, Object> response = new HashMap<>();
        CustomerVo result = customerService.getById(customerId);
        response.put("code", 200);
        response.put("data", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
