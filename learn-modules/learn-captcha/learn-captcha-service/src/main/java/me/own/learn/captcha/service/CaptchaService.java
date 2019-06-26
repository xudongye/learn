package me.own.learn.captcha.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author:simonye
 * @date 21:57 2019/6/26
 **/
public interface CaptchaService {

    Map<String, Object> generatorCodeImage(HttpServletRequest request, HttpServletResponse response);


}
