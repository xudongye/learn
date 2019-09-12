package me.own.learn.logistics.vo;

/**
 * @author yexudong
 * @date 2019/9/2 13:11
 */
public class LogisticsVo {

    private String resultcode;
    private String reason;
    private LogisticsResultVo result;
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

    public LogisticsResultVo getResult() {
        return result;
    }

    public void setResult(LogisticsResultVo result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
