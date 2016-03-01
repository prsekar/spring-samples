package org.samples.basics.config;

import org.samples.basics.beans.SimpleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by psekar on 2/26/16.
 */
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
