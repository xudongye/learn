package me.own.learn.elsearch.service.impl.listener;

import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.elsearch.bo.ProductDocBo;
import me.own.learn.elsearch.service.ElsearchService;
import me.own.learn.event.service.EventService;
import me.own.learn.event.service.message.product.ProductCreateMessage;
import me.own.learn.event.service.message.product.ProductDeleteMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class ElsearchEventReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElsearchEventReceiver.class);

    @Autowired
    private ElsearchService elsearchService;

    @JmsListener(destination = EventService.EventName.ProductEvent.PRODUCT_CREATE, containerFactory = "queueJmsListenerContainerFactory")
    public void onProductCreated(ProductCreateMessage createMessage) throws JMSException {
        ProductDocBo productDocBo = Mapper.Default().map(createMessage, ProductDocBo.class);
        elsearchService.save(productDocBo);
        LOGGER.info("save product chip on product {} created.", productDocBo.getProductName());
    }

    @JmsListener(destination = EventService.EventName.ProductEvent.PRODUCT_DELETE, containerFactory = "queueJmsListenerContainerFactory")
    public void onProductDeleted(ProductDeleteMessage deleteMessage) throws JMSException {
        elsearchService.delete(deleteMessage.getSkuNo());
        LOGGER.info("remove product chip on product {} deleted.", deleteMessage.getSkuNo());
    }
}
