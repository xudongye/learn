package me.own.learn.sync.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author:simonye
 * @date 21:23 2019/4/20
 **/
public class CountryExistException extends BusinessException {
    @Override
    public String getErrorCode() {
        return "20105";
    }

    @Override
    public String getErrorMsg() {
        return "country exist.";
    }
}
