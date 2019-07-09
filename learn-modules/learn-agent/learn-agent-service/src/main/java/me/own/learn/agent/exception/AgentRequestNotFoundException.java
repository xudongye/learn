package me.own.learn.agent.exception;

import me.own.commons.base.exception.ObjectNotFoundException;

/**
 * @author yexudong
 * @date 2019/7/5 10:06
 */
public class AgentRequestNotFoundException extends ObjectNotFoundException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "未找到代理商申请记录！";
    }
}
