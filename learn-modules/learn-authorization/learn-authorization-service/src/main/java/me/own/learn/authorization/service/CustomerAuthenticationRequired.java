package me.own.learn.authorization.service;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * the method signature must contain such type field:
 * CustomerAccessToken
 * HttpServletRequest
 *
 * @author yexudong
 * @date 2019/5/30 10:37
 * @see me.own.learn.authorization.service.model.CustomerAccessToken
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CustomerAuthenticationRequired {
}
