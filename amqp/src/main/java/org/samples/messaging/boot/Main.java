package org.samples.messaging.boot;

import org.samples.messaging.amqp.MessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Created by psekar on 2/24/16.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        //bootstrap the config and components
        AbstractApplicationContext context = new AnnotationConfigApplicationContext("org.samples.messaging");
        logger.info("registering a shutdown hook incase of jvm shutdown");
        context.registerShutdownHook();
        //AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
        //amqpTemplate.convertAndSend("Hello World");
        //System.out.println("Sent: Hello World");


        MessagePublisher publisher = context.getBean(MessagePublisher.class);
        publisher.send("Hello World");


        //dismantle the objects
        context.close();
    }
}
