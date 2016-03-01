package org.samples.test;

import org.junit.Assert;
import org.junit.Test;
import org.samples.basics.components.ComponentA;
import org.samples.basics.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by psekar on 2/26/16.
 */

public class LoadAndReadTest {
    @Test
    public void invokeComponentTest() {
        ApplicationContext annotationContext =  annotationContext = new AnnotationConfigApplicationContext("org.samples");
        ComponentA componentA = annotationContext.getBean(ComponentA.class);
        Assert.assertNotNull(componentA.start());
    }

    @Test
    public void loadPropertiesTest() {
        ApplicationContext annotationContext =  annotationContext = new AnnotationConfigApplicationContext("org.samples");
        AppConfig propertiesConfig = annotationContext.getBean(AppConfig.class);
        Assert.assertNotNull(propertiesConfig.getProperty("propertyBean1"));
    }
    
    @Test
    public void loadFromXML() {
        ApplicationContext contextFromXml = new ClassPathXmlApplicationContext("classpath:spring/application-context.xml");
        Assert.assertNotNull(contextFromXml.getBean("propertyBean1"));
    }


    @Test
    public void loadFromXMLWithSysProp() {
        System.setProperty("property.bean.2", "systemvalue2");
        ApplicationContext contextFromXml = new ClassPathXmlApplicationContext("classpath:spring/application-context.xml");

        Assert.assertEquals("systemvalue2", contextFromXml.getBean("propertyBean2"));
    }
}
