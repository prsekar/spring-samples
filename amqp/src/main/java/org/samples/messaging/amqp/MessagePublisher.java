package org.samples.messaging.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.samples.messaging.utils.AMQPConnectionFactory;

/**
 * Created by psekar on 2/24/16.
 */
@Component
public class MessagePublisher {
    private final RabbitTemplate rabbitTemplate;
    
    private static final Logger logger = LoggerFactory.getLogger(MessagePublisher.class);
    
    @Autowired
    public MessagePublisher(AMQPConnectionFactory amqpConnectionFactory,
                            @Value("${amqpExchange}") String exchange
                            //@Value("${amqpRetryMaxAttempts}") String amqpRetryMaxAttempts
                            ) {
        CachingConnectionFactory cachingConnectionFactory = amqpConnectionFactory.getConnectionFactory();
        this.rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setExchange("appExchange");
        
        //Add retry support
        RetryTemplate retryTemplate = new RetryTemplate();
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(5);
        
        //#TODO Add backoff policy
        retryTemplate.setRetryPolicy(retryPolicy);
        rabbitTemplate.setRetryTemplate(retryTemplate);
        
    }
    
    public void send(String message) {
        rabbitTemplate.convertAndSend(message);
    }
}
