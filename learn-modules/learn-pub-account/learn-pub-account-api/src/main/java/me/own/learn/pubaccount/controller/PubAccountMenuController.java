package me.own.learn.pubaccount.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.learn.pubaccount.service.MenuCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yexudong
 * @date 2018/11/13 16:15
 */
@RestController
@Api(value = "PubAccountMenuController", description = "公众号自定义菜单创建接口")
public class PubAccountMenuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PubAccountMenuController.class);

    @Autowired
    private MenuCreateService menuCreateService;

    @ApiOperation("刷新新建公众号菜单")
    @RequestMapping(value = "/api/learn/pub-accounts/{appId}/menu/create", method = RequestMethod.GET)
    public ResponseEntity createMenu(HttpServletRequest request, @PathVariable(value = "appId") String appId) {
        try {
            menuCreateService.createMenu(appId);
        } catch (Exception e) {
            LOGGER.error("create menu failed: " + e);
            return new ResponseEntity("create menu failed: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("success", HttpStatus.OK);
    }
}
