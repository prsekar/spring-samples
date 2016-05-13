package org.samples.basics.config;

import org.samples.basics.beans.SimpleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config1 {

    @Autowired
    private AppConfig config;
    @Bean
    public SimpleBean getBean1(){
        config.getProperty("propertyBean1");
        return new SimpleBean();
    }
    
    

}
