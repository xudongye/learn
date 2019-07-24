package me.own.learn.agent.exception;

import me.own.commons.base.exception.ObjectNotFoundException;

/**
 * @author yexudong
 * @date 2019/7/24 14:58
 */
public class AgentNotFoundException extends ObjectNotFoundException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "代理商未找到！";
    }
}
