package me.own.learn.authorization.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import me.own.learn.authorization.dto.AdminLoginDto;
import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.AdminLoginService;
import me.own.learn.authorization.service.TokenService;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.authorization.utils.CookieUtils;
import me.own.learn.authorization.vo.AdminTokenVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/5/30 11:18
 */
@RestController
@RequestMapping(value = "/api/v1/admin/tokens")
@Api(value = "管理员登录功能模块", description = "管理员登录")
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @Autowired
    private TokenService tokenService;

    @ApiOperation("管理员登录")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity adminLogin(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody AdminLoginDto loginBo
    ) {
        AdminTokenVo token = adminLoginService.login(loginBo);
        CookieUtils.setAdminUserTokenInCookie(request, response, token.getValue());
        return new ResponseEntity(token, HttpStatus.CREATED);
    }

    @ApiOperation("管理员登出")
    @RequestMapping(method = RequestMethod.DELETE)
    @AdminAuthenticationRequired
//    @ApiImplicitParam(name = "a_id", value = "调试模式", paramType = "query", dataType = "String", defaultValue = "1")
    public ResponseEntity logout(
            HttpServletRequest request,
            HttpServletResponse response,
            AdminAccessToken aat
    ) {
        Map<String, Object> responseBody = new HashMap<>();
        if (StringUtils.isEmpty(aat.getValue())) {
            responseBody.put("success", false);
            responseBody.put("msg", "异常退出");
            return new ResponseEntity(responseBody, HttpStatus.BAD_REQUEST);
        }
        tokenService.remove(aat.getValue());
        // set empty string to cookie(delete the token cookie)
        CookieUtils.setAdminUserTokenInCookie(request, response, "");
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
