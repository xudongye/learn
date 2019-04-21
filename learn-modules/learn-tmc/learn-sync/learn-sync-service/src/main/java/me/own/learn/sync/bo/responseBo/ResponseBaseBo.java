package me.own.learn.sync.bo.responseBo;

import java.io.Serializable;

/**
 * @author yexudong
 * @date 2019/4/19 10:45
 */
public class ResponseBaseBo<E> implements Serializable {
    private E bussinessResponse;
    private String retrunCode;
    private String retrunMsg;

    public E getBussinessResponse() {
        return bussinessResponse;
    }

    public void setBussinessResponse(E bussinessResponse) {
        this.bussinessResponse = bussinessResponse;
    }

    public String getRetrunCode() {
        return retrunCode;
    }

    public void setRetrunCode(String retrunCode) {
        this.retrunCode = retrunCode;
    }

    public String getRetrunMsg() {
        return retrunMsg;
    }

    public void setRetrunMsg(String retrunMsg) {
        this.retrunMsg = retrunMsg;
    }
}
