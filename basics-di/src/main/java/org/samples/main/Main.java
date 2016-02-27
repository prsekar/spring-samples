package org.samples.main;

import org.samples.components.ComponentA;
import org.samples.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by psekar on 2/26/16.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        // This applicationContext reads from @configuration from a defined package
        logger.info("Loading AnnotationConfigApplicationContext from classes");
        ApplicationContext context = new AnnotationConfigApplicationContext("org.samples");

        //Load XML file and read properties in that file
        logger.info("Loading ClassPathXmlApplicationContext from xml");
        ApplicationContext contextFromXml = new ClassPathXmlApplicationContext("classpath:spring/application-context.xml");
        logger.info("Value for propertyBean1 : {}", contextFromXml.getBean("propertyBean1"));
        logger.info("Value for propertyBean2 : {}", contextFromXml.getBean("propertyBean2"));

        //Configure a PropertiesConfigurator class and read it across the src package
        logger.info("Get the properties object from AnnotationConfigApplicationContext");
        AppConfig propertiesConfig = context.getBean(AppConfig.class);
        logger.info("Value for property propertyBean1 : {} ", propertiesConfig.getProperty("propertyBean1"));

        ComponentA componentA = context.getBean(ComponentA.class);
        componentA.start();
    }
}
