package me.own.learn.sync.bo.responseBo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author yexudong
 * @date 2019/4/19 10:45
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBaseBo<E> implements Serializable {
    private static final long serialVersionUID = 4543583458127221425L;
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
