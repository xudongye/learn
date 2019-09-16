package me.own.learn.order.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.utils.request.RequestUtils;
import me.own.learn.authorization.service.CustomerAuthenticationRequired;
import me.own.learn.authorization.service.model.CustomerAccessToken;
import me.own.learn.order.constant.OrderConstant;
import me.own.learn.order.dto.OrderDto;
import me.own.learn.order.service.OrderAddressService;
import me.own.learn.order.service.OrderQueryCondition;
import me.own.learn.order.service.OrderService;
import me.own.learn.order.vo.OrderAddressVo;
import me.own.learn.order.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/orders/customer")
@Api(value = "会员订单功能模块", description = "会员订单管理模块")
public class OrderCustomerController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderAddressService addressService;

    @ApiOperation("新建订单")
    @RequestMapping(method = RequestMethod.POST)
    @CustomerAuthenticationRequired
    @ApiImplicitParam(name = "c_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity onCreate(HttpServletRequest request, CustomerAccessToken cat,
                                   @RequestBody OrderDto orderDto) {

        Map<String, Object> response = new HashMap<>();
        orderDto.setCustomerId(cat.getCustomerId());
        OrderVo orderVo = orderService.create(orderDto);
        response.put("order", orderVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("批量搜索订单")
    @RequestMapping(method = RequestMethod.POST)
    @CustomerAuthenticationRequired
    @ApiImplicitParam(name = "c_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity onPage(HttpServletRequest request, CustomerAccessToken cat,
                                 @RequestParam(required = false, defaultValue = "1") int pageNumber,
                                 @RequestParam(required = false, defaultValue = "10") int pageSize,
                                 @RequestParam(required = false) OrderQueryCondition condition) {

        if (condition == null) {
            condition = RequestUtils.buildQueryFilter(request, OrderQueryCondition.class);
        }
        condition.setCustomerId(cat.getCustomerId());
        Map<String, Object> response = new HashMap<>();
        PageQueryResult<OrderVo> result = orderService.page(pageNumber, pageSize, condition);
        response.put("orders", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("修改订单状态")
    @RequestMapping(method = RequestMethod.PUT)
    @CustomerAuthenticationRequired
    @ApiImplicitParam(name = "c_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity onChangeStatus(HttpServletRequest request, CustomerAccessToken cat,
                                         @RequestParam String orderNo,
                                         @RequestParam OrderConstant.OrderStatus status) {
        Map<String, Object> response = new HashMap<>();
        OrderVo orderVo = orderService.updateStatusByOrderNo(orderNo, status);
        response.put("order", orderVo);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }


    @ApiOperation("订单地址使用接口")
    @RequestMapping(value = "/address", method = RequestMethod.GET)
    @CustomerAuthenticationRequired
    @ApiImplicitParam(name = "c_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity onGetAddress(HttpServletRequest request, CustomerAccessToken cat) {

        Map<String, Object> response = new HashMap<>();
        List<OrderAddressVo> addressVos = addressService.listAddressByCustomerId(cat.getCustomerId());
        response.put("address", addressVos);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
