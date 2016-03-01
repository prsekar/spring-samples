package org.samples.messaging.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by psekar on 2/29/16.
 */
@Configuration
public class AppConfig {

    @Bean
    public ApplicationContext getAppContext() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        return appContext;
    }
}
