package me.own.learn.sync.bo;

/**
 * @author:simonye
 * @date 17:57 2019/4/20
 **/
public class BusinessRequestBo<T> {

    private T businessRequest;

    public BusinessRequestBo() {
    }

    public BusinessRequestBo(T businessRequest) {
        this.businessRequest = businessRequest;
    }

    public T getBusinessRequest() {
        return businessRequest;
    }

    public void setBusinessRequest(T businessRequest) {
        this.businessRequest = businessRequest;
    }
}
