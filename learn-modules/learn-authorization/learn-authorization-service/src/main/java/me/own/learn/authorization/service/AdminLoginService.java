package me.own.learn.authorization.service;

import me.own.learn.authorization.dto.AdminLoginDto;
import me.own.learn.authorization.vo.AdminTokenVo;

/**
 * @author yexudong
 * @date 2019/5/30 10:14
 */
public interface AdminLoginService {

    AdminTokenVo login(AdminLoginDto loginDto);

    void logout(String tokenValue);
}
