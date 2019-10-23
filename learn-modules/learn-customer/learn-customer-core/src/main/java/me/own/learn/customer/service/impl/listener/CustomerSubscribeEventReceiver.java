package me.own.learn.customer.service.impl.listener;

import me.own.learn.customer.dto.CustomerDto;
import me.own.learn.customer.service.CustomerService;
import me.own.learn.customer.vo.CustomerVo;
import me.own.learn.event.service.EventService;
import me.own.learn.event.service.message.customer.CustomerScanQRCodeMessage;
import me.own.learn.event.service.message.customer.CustomerSubscribeMessage;
import me.own.learn.event.service.message.customer.CustomerUnSubscribeMessage;
import me.own.learn.pubaccount.service.PubAccountUserService;
import me.own.learn.pubaccount.service.model.UserInfoBo;
import me.own.learn.pubconfiguration.service.PubConfigurationService;
import me.own.learn.pubconfiguration.vo.PubConfigurationVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.util.Date;

@Component
public class CustomerSubscribeEventReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerSubscribeEventReceiver.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PubConfigurationService pubConfigurationService;

    @Autowired
    private PubAccountUserService pubAccountUserService;

    @Autowired
    private EventService eventService;

    @JmsListener(destination = EventService.EventName.CustomerEvent.CUSTOMER_SUBSCRIBE, containerFactory = "queueJmsListenerContainerFactory")
    public void onCustomerSubscribe(CustomerSubscribeMessage msgBody) throws JMSException {
        String openId = msgBody.getCustomerOpenId();
        String eventKey = msgBody.getEventKey();
        String appId = msgBody.getAppId();
        PubConfigurationVo tenant = pubConfigurationService.getByAppId(appId);
        CustomerVo customerVo = customerService.getByOpenId(openId, tenant.getId());
        boolean newCustomer = customerVo == null;
        long customerId;
        if (newCustomer) {
            UserInfoBo userInfo = pubAccountUserService.getUserInfo(appId, openId);
            CustomerDto customerCreateDto = adapt(userInfo);
            customerCreateDto.setSourceAgentId(eventKey);
            customerCreateDto.setPubAccountId(tenant.getId());
            CustomerVo customer = customerService.createFromPubAccount(customerCreateDto);
            customerId = customer.getId();
            LOGGER.info("new customer {} (openid {}, nickname: {}) created by subscribe pub account(parameter QR code: {}).", customerId, openId, userInfo.getNickname(), eventKey);
        } else {
            customerId = customerVo.getId();
            customerService.updateSubscribeStatus(customerId, true);
            LOGGER.info("old customer {} (openid {}) subscribe pub account again, event key:{}", customerId, openId, eventKey);
            if (eventKey.startsWith("qrscene_")) {
                eventService.enqueue(EventService.EventName.CustomerEvent.CUSTOMER_SCAN_QRCODE, new CustomerScanQRCodeMessage(customerId, eventKey));
            }
        }
    }

    @JmsListener(destination = EventService.EventName.CustomerEvent.CUSTOMER_UNSUBSCRIBE, containerFactory = "queueJmsListenerContainerFactory")
    public void onCustomerUnsubscribe(CustomerUnSubscribeMessage msgBody) throws JMSException {
        PubConfigurationVo tenant = pubConfigurationService.getByAppId(msgBody.getAppId());
        long customerId = customerService.getByOpenId(msgBody.getCustomerOpenId(), tenant.getId()).getId();
        customerService.updateSubscribeStatus(customerId, false);
        LOGGER.info("customer {} (openid {}) unsubscribe pub account", customerId, msgBody.getCustomerOpenId());
    }

    private CustomerDto adapt(UserInfoBo userInfoBo) {
        CustomerDto createDto = new CustomerDto();
        createDto.setSource(CustomerDto.WECHAT);
        createDto.setOpenid(userInfoBo.getOpenid());
        createDto.setNickName(userInfoBo.getNickname());
        createDto.setSubscribed(userInfoBo.getSubscribe());
        createDto.setSex(userInfoBo.getSex());
        createDto.setHeadImage(userInfoBo.getHeadimgurl());
        createDto.setProvince(userInfoBo.getProvince());
        createDto.setCity(userInfoBo.getCity());
        createDto.setSubscribeTime(new Date(userInfoBo.getSubscribe_time()));
        createDto.setCreateTime(new Date());
        return createDto;
    }
}
