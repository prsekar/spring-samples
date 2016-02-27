package org.samples.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * Created by psekar on 2/26/16.
 */
@Configuration
@ComponentScan("org.samples.*")
@PropertySources(value = {
        @PropertySource(value = {"classpath:application.properties", "file:${CONF_DIR}/application.properties"},
                ignoreResourceNotFound = true)
})
public class AppConfig {

    @Autowired
    private AbstractBeanFactory beanFactory;

    public String getProperty(String name) {
        return beanFactory.resolveEmbeddedValue("${" + name + "}");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig(Environment environment) {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setEnvironment(environment);
        return propertySourcesPlaceholderConfigurer;
    }
}
