package me.own.learn.pubconfiguration.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/5/31 11:23
 */
public class PubConfigurationExistException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "";
    }

    @Override
    public String getErrorMsg() {
        return "公众号已经配置！";
    }
}
