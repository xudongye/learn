package me.own.learn.chat.model;

/**
 * @author: yexudong
 * @Date: 2020/3/30 17:09
 */
public class LinkModel {

    private long msgCount;
    private boolean refreshChannel;

    public long getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(long msgCount) {
        this.msgCount = msgCount;
    }

    public boolean isRefreshChannel() {
        return refreshChannel;
    }

    public void setRefreshChannel(boolean refreshChannel) {
        this.refreshChannel = refreshChannel;
    }
}
