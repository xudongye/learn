package me.own.learn.pubconfiguration.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/5/31 11:02
 */
public class PubConfigurationNotFoundException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "";
    }

    @Override
    public String getErrorMsg() {
        return "未找到公众号配置信息！";
    }
}
