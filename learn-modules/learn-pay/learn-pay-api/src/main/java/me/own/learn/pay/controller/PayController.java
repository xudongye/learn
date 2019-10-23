package me.own.learn.pay.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.own.commons.base.utils.request.RequestUtils;
import me.own.learn.authorization.service.CustomerAuthenticationRequired;
import me.own.learn.authorization.service.model.CustomerAccessToken;
import me.own.learn.customer.service.CustomerService;
import me.own.learn.customer.vo.CustomerVo;
import me.own.learn.pay.bo.PayBusinessBo;
import me.own.learn.pay.service.PayService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1/payments")
@Api(value = "支付模块", description = "微信，支付宝支付接口")
public class PayController {


    final private static Logger logger = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private PayService payService;

    @Autowired
    private CustomerService customerService;

    @ApiOperation("统一支付接口")
    @RequestMapping(method = RequestMethod.POST)
    @CustomerAuthenticationRequired
    public ResponseEntity createUnifiedOrder(HttpServletRequest request,
                                             CustomerAccessToken cat,
                                             @RequestBody PayBusinessBo payBo) {
        if (payBo.getPayMethod().equals("wxpay")) {
            return getWxPayResponseEntity(request, cat, payBo);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity getWxPayResponseEntity(HttpServletRequest request, CustomerAccessToken cat, PayBusinessBo payBo) {

        // Set customer's open id for "JSAPI" wxpay method
        if (payBo.getTradeType().equals("JSAPI")) {
            CustomerVo customerDto = customerService.getById(cat.getCustomerId());
            if (StringUtils.isEmpty(customerDto.getOpenid())) {
                return new ResponseEntity<>("customer's openid not found", HttpStatus.BAD_REQUEST);
            }
            payBo.setOpenId(customerDto.getOpenid());
        }

        // set spbill_create_ip for "MWEB" wxpay method
        if (payBo.getTradeType().equals("MWEB")) {
            payBo.setSpBillCreateIp(RequestUtils.getRemoteAddressIp(request));
            logger.debug("wx h5 pay set spbill_create_ip : {}, fingerprint: {}", payBo.getSpBillCreateIp(), payBo.getFingerPrint());
        }

        return new ResponseEntity<>(
                payService.createWxPayUnifiedBusiness(payBo),
                HttpStatus.CREATED
        );
    }
}
