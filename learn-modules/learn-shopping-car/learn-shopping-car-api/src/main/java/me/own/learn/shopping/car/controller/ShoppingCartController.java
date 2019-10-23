package me.own.learn.shopping.car.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.utils.price.PriceUtils;
import me.own.learn.authorization.service.CustomerAuthenticationRequired;
import me.own.learn.authorization.service.model.CustomerAccessToken;
import me.own.learn.shoppingcar.dto.ShoppingCartDto;
import me.own.learn.shoppingcar.service.ShoppingCartService;
import me.own.learn.shoppingcar.vo.ShoppingCartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/shopping-carts")
@Api(value = "购物车模块", description = "购物车接口")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    @ApiOperation("添加购物车")
    @CustomerAuthenticationRequired
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity createShoppingCartItem(HttpServletRequest request, CustomerAccessToken cat,
                                                 @RequestBody ShoppingCartDto shoppingCartDto) {
        Map<String, Object> response = new HashMap<>();
        shoppingCartDto.setCustomerId(cat.getCustomerId());
        ShoppingCartVo shoppingCartVo = shoppingCartService.create(shoppingCartDto);
        response.put("shoppingCart", shoppingCartVo);
        response.put("msg", "create shoppingCart success!");
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation("会员用户获取购物车列表")
    @CustomerAuthenticationRequired
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getShoppingCartItemList(HttpServletRequest request, CustomerAccessToken cat,
                                                  @RequestParam(value = "ids", required = false) List<Long> ids,
                                                  @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Map<String, Object> response = new HashMap<>();
        // Calculate total price
        double totalPrice = shoppingCartService.getAllShoppingCartItemPrice(cat.getCustomerId(), ids);
        totalPrice = PriceUtils.ConvertDecimalPoint(totalPrice, 2);
        response.put("totalPrice", totalPrice);

        // query list of items
        PageQueryResult<ShoppingCartVo> queryResult = shoppingCartService.page(pageNumber, pageSize, cat.getCustomerId(), ids);
        response.put("count", queryResult.getTotalCount());
        response.put("shoppingCartItems", queryResult.getItems());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation("修改购物车商品数量")
    @CustomerAuthenticationRequired
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateQuantity(HttpServletRequest request, CustomerAccessToken cat, @PathVariable(value = "id") long id) {
        return null;
    }
}
