package org.samples.messaging.utils;

import com.rabbitmq.client.ConnectionFactory;
import org.samples.messaging.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by psekar on 2/24/16.
 */
@Component
@ComponentScan("org.samples.messaging")
public class AMQPConnectionFactory {
    private static final Logger logger = LoggerFactory.getLogger(AMQPConnectionFactory.class);
    private CachingConnectionFactory connectionFactory = null;

    @Autowired
    public AMQPConnectionFactory(AppConfig appConfig) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory rabbitConnectionFactory = new ConnectionFactory();

        ApplicationContext appContext = appConfig.getAppContext();
        
        URI amqpUri = (URI) appContext.getBean("amqpUri");
        rabbitConnectionFactory.setUri(amqpUri);
        // TODO default org.samples.messaging.amqp disabled, we can keep it configurable later
        
        String username = (String) appContext.getBean("amqpUser");
        String password = (String) appContext.getBean("amqpPassword");
        rabbitConnectionFactory.setUsername(username);
        rabbitConnectionFactory.setPassword(password);

        // Spring org.samples.messaging.amqp configs
        this.connectionFactory = new CachingConnectionFactory(rabbitConnectionFactory);
        // set heartbeat to 10 seconds
        this.connectionFactory.setRequestedHeartBeat(10);
    }
    
    public CachingConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

}
