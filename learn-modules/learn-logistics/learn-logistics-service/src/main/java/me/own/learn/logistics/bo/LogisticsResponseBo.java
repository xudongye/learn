package me.own.learn.logistics.bo;

import java.util.List;

/**
 * @author yexudong
 * @date 2019/9/4 17:43
 */
public class LogisticsResponseBo<E> {
    private String resultcode;
    private String reason;
    private E result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public E getResult() {
        return result;
    }

    public void setResult(E result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    @Override
    public String toString() {
        return "LogisticsResponseBo{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }
}
