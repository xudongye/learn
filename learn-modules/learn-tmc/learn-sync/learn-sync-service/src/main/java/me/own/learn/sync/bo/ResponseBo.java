package me.own.learn.sync.bo;

/**
 * @author yexudong
 * @date 2019/4/19 10:45
 */
public class ResponseBo {
    private Object bussinessResponse;
    private String retrunCode;
    private String retrunMsg;

    public Object getBussinessResponse() {
        return bussinessResponse;
    }

    public void setBussinessResponse(Object bussinessResponse) {
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
