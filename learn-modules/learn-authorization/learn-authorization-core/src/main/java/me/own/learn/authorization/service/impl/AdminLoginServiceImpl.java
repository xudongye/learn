package me.own.learn.authorization.service.impl;

import me.own.learn.admin.service.AdminService;
import me.own.learn.admin.vo.AdminVo;
import me.own.learn.authorization.dto.AdminLoginDto;
import me.own.learn.authorization.exception.PasswordWrongException;
import me.own.learn.authorization.service.AdminLoginService;
import me.own.learn.authorization.service.TokenService;
import me.own.learn.authorization.vo.AdminTokenVo;
import me.own.learn.authorization.vo.TokenVo;
import me.own.commons.base.utils.encode.MD5;
import me.own.commons.base.utils.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yexudong
 * @date 2019/5/30 10:17
 */
@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminLoginServiceImpl.class);

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AdminService adminService;

    @Override
    public AdminTokenVo login(AdminLoginDto loginDto) {
        AdminVo adminVo = adminService.getByLoginLabel(loginDto.getLoginLabel());
        if (!adminVo.getPassword().equals(MD5.getMD5Code(loginDto.getPassword()))) {
            throw new PasswordWrongException();
        }
        TokenVo adminTokenVo = tokenService.createFromAdmin(adminVo.getId(), adminVo.getName());
        LOGGER.info("admin {} login , get token {}", adminVo.getName(), adminTokenVo.getValue());
        return Mapper.Default().map(adminTokenVo, AdminTokenVo.class);
    }

    @Override
    public void logout(String tokenValue) {
        tokenService.remove(tokenValue);
    }
}
