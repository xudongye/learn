package me.own.learn.logistics.bo;

/**
 * @author yexudong
 * @date 2019/9/2 13:19
 */
public class LogisticsRequestBo {
    //需要查询的快递公司编号
    private String com;
    //需要查询的快递单号
    private String no;
    //寄件人手机号后四位，顺丰快递需要提供senderPhone和receiverPhone其中一个
    private String senderPhone;
    //收件人手机号后四位，顺丰快递需要提供senderPhone和receiverPhone其中一个
    private String receiverPhone;
    //在个人中心->我的数据,接口名称上方查看
    private String key;
    //返回数据的格式,xml或json，默认json
    private String dtype;

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }
}
