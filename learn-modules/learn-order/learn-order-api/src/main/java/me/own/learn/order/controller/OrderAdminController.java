package me.own.learn.order.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.utils.request.RequestUtils;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.CustomerAuthenticationRequired;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.authorization.service.model.CustomerAccessToken;
import me.own.learn.order.service.OrderQueryCondition;
import me.own.learn.order.service.OrderService;
import me.own.learn.order.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/api/v1/orders/admin")
@Api(value = "管理员订单功能模块", description = "管理员订单管理模块")
public class OrderAdminController {

    @Autowired
    private OrderService orderService;


    @ApiOperation("后台管理系统批量搜索订单")
    @RequestMapping(method = RequestMethod.POST)
    @AdminAuthenticationRequired
    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity onPage(HttpServletRequest request, AdminAccessToken aat,
                                 @RequestParam(required = false, defaultValue = "1") int pageNumber,
                                 @RequestParam(required = false, defaultValue = "10") int pageSize,
                                 @RequestParam(required = false) OrderQueryCondition condition) {

        if (condition == null) {
            condition = RequestUtils.buildQueryFilter(request, OrderQueryCondition.class);
        }
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<OrderVo> result = orderService.page(pageNumber, pageSize, condition);
        response.put("orders", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
