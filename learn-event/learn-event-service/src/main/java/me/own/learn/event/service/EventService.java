package me.own.learn.event.service;

/**
 * @author yexudong
 * @date 2019/6/28 17:25
 */
public interface EventService {

    void publish(String topicDestination, MessageCarriable message);

    void enqueue(String queueDestination, MessageCarriable message);
}
