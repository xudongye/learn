package me.own.learn.sync.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author:simonye
 * @date 17:38 2019/4/20
 **/
public class CountryNotFoundException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "20104";
    }

    @Override
    public String getErrorMsg() {
        return "country not found.";
    }
}
