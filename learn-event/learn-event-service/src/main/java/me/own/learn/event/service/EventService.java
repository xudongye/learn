package me.own.learn.event.service;

/**
 * @author yexudong
 * @date 2019/6/28 17:25
 */
public interface EventService {

    void publish(String topicDestination, MessageCarriable message);

    void enqueue(String queueDestination, MessageCarriable message);

    interface EventName {
        interface AgentEvent {
            String AGENT_PASS = "AGENT_PASS";
        }

        interface CustomerEvent {
            String CUSTOMER_SUBSCRIBE = "CUSTOMER_SUBSCRIBE";
            String CUSTOMER_UNSUBSCRIBE = "CUSTOMER_UNSUBSCRIBE";
            String CUSTOMER_SCAN_QRCODE = "CUSTOMER_SCAN_QRCODE";
        }

        interface ProductEvent {
            String PRODUCT_CREATE = "PRODUCT_CREATE";
            String PRODUCT_UPDATE = "PRODUCT_UPDATE";
            String PRODUCT_DELETE = "PRODUCT_DELETE";
        }
    }
}
