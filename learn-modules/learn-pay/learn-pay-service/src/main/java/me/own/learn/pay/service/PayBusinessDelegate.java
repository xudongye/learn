package me.own.learn.pay.service;

import me.own.learn.pay.bo.PayBusinessBo;

public interface PayBusinessDelegate {

    /***
     * Check if any business entity has already been paid
     * @return
     */
    boolean isAlreadyPaid(PayBusinessBo payBusinessBo);

    /***
     * Get 100 paid price of business entity list
     * @return the paid price
     */
    double getPaidPrice(PayBusinessBo payBusinessBo);

    /***
     * Define the title text displayed on 3rd payment
     * @return
     */
    String getSubject();

    /***
     * Define the body text displayed on 3rd payment
     * @return
     */
    String getBody();

    /***
     * Handle pay result
     */
    void handlePayResult(PayBusinessBo payBusinessBo);
}
