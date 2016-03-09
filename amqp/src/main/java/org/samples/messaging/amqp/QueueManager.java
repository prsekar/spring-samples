package org.samples.messaging.amqp;

import org.samples.messaging.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.samples.messaging.utils.AMQPConnectionFactory;

import java.util.Map;

/**
 * Created by psekar on 2/24/16.
 */
@Configuration
public class QueueManager {
    private static final Logger logger = LoggerFactory.getLogger(QueueManager.class);
    private static final boolean DURABLE = true;
    private static final boolean NOT_EXCLUSIVE = false;
    private static final boolean AUTODELETE_OFF = false;
    private static final Map<String, Object> NO_ARUGMENTS = null;

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private AMQPConnectionFactory connectionFactory;
    
    @Autowired
    private AppConfig appConfig;


    public void configure() {
        logger.info("Configure the required exchanges, queues and bindings");
        Object rabbitAdmin = applicationContext.getBean("rabbitAdmin");
        Object rpcExchange = applicationContext.getBean("rpcExchange");
        Object rpcQueue = applicationContext.getBean("rpcQueue", rabbitAdmin);
        applicationContext.getBean("rpcBinding", rabbitAdmin, rpcQueue, rpcExchange);

    }
    
    @Bean
    public TopicExchange rpcExchange(RabbitAdmin rabbitAdmin) {
        TopicExchange exchange = new TopicExchange("appExchange");
        rabbitAdmin.declareExchange(exchange);
        return exchange;
    }
    
    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory.getConnectionFactory());
    }
    
    @Bean
    public Queue rpcQueue(RabbitAdmin rabbitAdmin)  {
        Queue queue = new Queue("consumerqueue", DURABLE, NOT_EXCLUSIVE,
                AUTODELETE_OFF, NO_ARUGMENTS);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
    
    @Bean
    public Binding rpcBinding(RabbitAdmin rabbitAdmin, Queue rpcQueue, TopicExchange exchange) {
        Binding binding = BindingBuilder.bind(rpcQueue).to(exchange).with("#");
        rabbitAdmin.declareBinding(binding);
        return binding;
        
    }
}
