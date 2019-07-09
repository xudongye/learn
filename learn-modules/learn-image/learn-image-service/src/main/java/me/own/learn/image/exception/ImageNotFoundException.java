package me.own.learn.image.exception;

import me.own.commons.base.exception.ObjectNotFoundException;

/**
 * @author yexudong
 * @date 2019/7/3 14:33
 */
public class ImageNotFoundException extends ObjectNotFoundException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "图片不存在！";
    }
}
