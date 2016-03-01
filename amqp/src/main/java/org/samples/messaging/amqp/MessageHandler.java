package org.samples.messaging.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * Created by psekar on 2/24/16.
 */
@Component
public class MessageHandler implements MessageListener {
    private final Logger logger = LoggerFactory.getLogger(MessageHandler.class);
    @Override
    public void onMessage(Message message) {
        logger.info("Message received" + message.toString());
    }
}
