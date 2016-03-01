package org.samples.basics.components;

import org.samples.basics.config.Config1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Created by psekar on 2/26/16.
 */
@Component
@ComponentScan("org.samples")
public class ComponentA {
    private static final Logger logger = LoggerFactory.getLogger(ComponentA.class);
    @Autowired
    private Config1 config1;
    
    public String start() {
        logger.info("Loading bean value from autowired config1");
        logger.info("Get bean value " + config1.getBean1().getName());
        return config1.getBean1().getName();
    }
}
