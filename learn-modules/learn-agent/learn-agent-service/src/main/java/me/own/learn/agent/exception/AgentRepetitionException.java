package me.own.learn.agent.exception;

import me.own.commons.base.exception.BusinessException;

/**
 * @author yexudong
 * @date 2019/7/24 15:45
 */
public class AgentRepetitionException extends BusinessException {
    @Override
    public String getErrorCode() {
        return null;
    }

    @Override
    public String getErrorMsg() {
        return "该会员已经是代理商！";
    }
}
