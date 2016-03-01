package org.samples.messaging.amqp;

import org.samples.messaging.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.samples.messaging.utils.AMQPConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by psekar on 2/24/16.
 */
@Component
public class MessageSubscriber {
    private static Logger logger = LoggerFactory.getLogger(MessageSubscriber.class);

    @Autowired
    public MessageSubscriber(QueueManager queueManager,
                             MessageHandler messageHandler,
                             AMQPConnectionFactory connectionFactory,
                             AppConfig appConfig) throws IOException {

        queueManager.configure();

        CachingConnectionFactory cachingConnectionFactory = connectionFactory.getConnectionFactory();

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
        String[] queueNames = new String[]{"consumerqueue"};
        container.setQueueNames(queueNames);
        container.setMessageListener(new MessageListenerAdapter(messageHandler));
        logger.info("Created a message listener for queues consumerqueue");
        container.start();
    }
}
