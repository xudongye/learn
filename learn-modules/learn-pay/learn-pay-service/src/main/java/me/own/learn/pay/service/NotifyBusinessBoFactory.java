package me.own.learn.pay.service;

import me.own.learn.pay.bo.NotifyBusinessBo;

public interface NotifyBusinessBoFactory {

    /**
     * get Notify Bo used by notification
     */
    NotifyBusinessBo getNotifyBusinessBo();
}
