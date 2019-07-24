package me.own.learn.admin.service.impl.listener;

import me.own.learn.admin.dto.AdminDto;
import me.own.learn.admin.service.AdminService;
import me.own.learn.admin.vo.AdminVo;
import me.own.learn.configuration.delegate.LearnConfigurationServiceDelegate;
import me.own.learn.event.service.EventService;
import me.own.learn.event.service.message.agent.AgentMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

/**
 * @author yexudong
 * @date 2019/7/24 11:27
 */
@Component
public class AdminEventReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminEventReceiver.class);

    @Autowired
    private AdminService adminService;

    @JmsListener(destination = EventService.EventName.AgentEvent.AGENT_PASS, containerFactory = "queueJmsListenerContainerFactory")
    public void onAgentPass(AgentMessage agentMessage) throws JMSException {


    }
}
