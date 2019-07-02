package me.own.learn.event.service.service.impl;

import me.own.learn.event.service.EventService;
import me.own.learn.event.service.MessageCarriable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author yexudong
 * @date 2019/6/28 17:31
 */
@Component
public class EventServiceImpl implements EventService {

    @Autowired(required = false)
    @Qualifier(value = "learnTopicJmsTemplate")
    private JmsTemplate topicJmsTemplate;

    @Autowired(required = false)
    @Qualifier(value = "learnQueueJmsTemplate")
    private JmsTemplate queueJmsTemplate;

    @Override
    public void publish(String topicDestination, MessageCarriable message) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    doPublish(topicDestination, message);
                }
            });
        } else {
            doPublish(topicDestination, message);
        }
    }

    private void doPublish(String topicDestination, final MessageCarriable message) {
        topicJmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return topicJmsTemplate.getMessageConverter().toMessage(message, session);
            }
        });
    }

    @Override
    public void enqueue(String queueDestination, MessageCarriable message) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    doQueue(queueDestination, message);
                }
            });
        } else {
            doQueue(queueDestination, message);
        }
    }

    private void doQueue(String queueDestination, final MessageCarriable message) {
        queueJmsTemplate.send(queueDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return queueJmsTemplate.getMessageConverter().toMessage(message, session);
            }
        });
    }
}
