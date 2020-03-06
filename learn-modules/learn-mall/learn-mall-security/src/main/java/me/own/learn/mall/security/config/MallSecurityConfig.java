package me.own.learn.mall.security.config;

import me.own.learn.mall.aythority.service.MallAdminService;
import me.own.learn.mall.aythority.vo.MallAdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author: yexudong
 * @Date: 2020/3/6 10:40
 */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MallSecurityConfig extends SecurityConfig {

    @Autowired
    private MallAdminService adminService;

    @Bean
    public UserDetailsService userDetailsService() {

        //获取登录用户信息
        return this::loginByUsername;
    }

    private UserDetails loginByUsername(String username) {
        MallAdminVo adminVo = adminService.getByUsername(username);
        return null;
    }
}
