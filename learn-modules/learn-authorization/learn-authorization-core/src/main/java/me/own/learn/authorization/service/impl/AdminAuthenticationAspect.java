package me.own.learn.authorization.service.impl;

import me.own.learn.authorization.service.AdminAuthenticationRequired;
import me.own.learn.authorization.service.TokenService;
import me.own.learn.authorization.service.model.AdminAccessToken;
import me.own.learn.authorization.utils.CookieUtils;
import me.own.learn.authorization.vo.TokenVo;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/5/30 10:41
 */
@Component
@Aspect
public class AdminAuthenticationAspect {

    @Autowired
    private TokenService tokenService;

    private static Boolean debug;

    @Around(value = "@annotation(AdminAuthenticationRequired) && @annotation(RequestMapping)", argNames = "RequestMapping, AdminAuthenticationRequired")
    public Object authenticate(ProceedingJoinPoint joinPoint, RequestMapping mapping, AdminAuthenticationRequired required) throws Throwable {
        Object finalResult;
        ControllerMethodParameterPicker parameterPicker = new ControllerMethodParameterPicker(joinPoint).invoke();
        HttpServletRequest request = parameterPicker.getRequest();
        AdminAccessToken aat = parameterPicker.getAat();
        Map<String, Object> authResponse = new HashMap<>();
        if (debug == null) {
            String debugMode = request.getSession().getServletContext().getInitParameter("debugMode");
            debug = StringUtils.isNotEmpty(debugMode) && Boolean.valueOf(debugMode);
        }
        // 调试模式：如果调用者用c_id声明身份则直接放行
        String a_id = request.getParameter("a_id");
        if (debug && StringUtils.isNotEmpty(a_id)) {
            aat.setAdminId(Long.valueOf(a_id));
            return joinPoint.proceed();
        }
        // get token from cookie or request parameters
        String token = CookieUtils.getAdminUserTokenInCookie(request);
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("adminToken");
        }
        if (token == null) {
            authResponse.put("success", false);
            authResponse.put("error_msg", "系统访问令牌为空，请重新登录！");
            return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);
        } else {
            TokenVo tokenVo = tokenService.getByTokenValue(token);
            if (null == tokenVo || null == tokenVo.getAdminId()) {
                authResponse.put("success", false);
                authResponse.put("error_msg", "系统访问令牌错误，请重新登录！");
                return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);
            } else {
                if (!tokenService.isConsistent(token) && tokenService.isExpired(token)) {
                    authResponse.put("success", false);
                    authResponse.put("error_msg", "系统访问令牌过期，请重新登录！");
                    return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);
                }
                // set admin user id to controller input parameter
                if (aat == null) {
                    aat = new AdminAccessToken();
                }
                aat.setAdminId(tokenVo.getAdminId());
                aat.setValue(token);
                finalResult = joinPoint.proceed();
            }
        }
        return finalResult;
    }

    private class ControllerMethodParameterPicker {
        private ProceedingJoinPoint joinPoint;
        private HttpServletRequest request;
        private AdminAccessToken aat;

        public ControllerMethodParameterPicker(ProceedingJoinPoint joinPoint) {
            this.joinPoint = joinPoint;
        }

        public HttpServletRequest getRequest() {
            return request;
        }

        public AdminAccessToken getAat() {
            return aat;
        }

        public ControllerMethodParameterPicker invoke() {
            request = null;
            aat = null;
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                if (arg instanceof HttpServletRequest) {
                    request = (HttpServletRequest) arg;
                }
                if (arg instanceof AdminAccessToken) {
                    aat = (AdminAccessToken) arg;
                }
            }
            if (request == null || aat == null) {
                throw new IllegalArgumentException("no arguments: instance of javax.servlet.http.HttpServletRequest & me.own.learn.authorization.service.model.AdminAccessToken.");
            }
            return this;
        }
    }
}
