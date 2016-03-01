package org.samples.basics.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by psekar on 2/26/16.
 */
@Configuration
public class SimpleBean {
    @Value("${propertyBean1}")
    private String name;
    
    public String getName() {
        return name;
    }
}
