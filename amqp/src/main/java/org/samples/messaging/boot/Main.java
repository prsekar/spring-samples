package org.samples.messaging.boot;

import org.samples.messaging.amqp.MessagePublisher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by psekar on 2/24/16.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("org.samples.messaging");
        //AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);
        //amqpTemplate.convertAndSend("Hello World");
        //System.out.println("Sent: Hello World");
        
        MessagePublisher publisher = context.getBean(MessagePublisher.class);
        publisher.send("Hello World");
    }
}
