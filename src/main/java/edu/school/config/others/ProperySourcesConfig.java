package edu.school.config.others;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(
          value={"classpath:properties/${my.placeholder:activeMQ}/environment.properties"},
          ignoreResourceNotFound = true) //Assuming that "my.placeholder" is present in one of the 
                                            //property sources already registered, e.g. system properties or environment variables,
                                            //the placeholder will be resolved to the corresponding value. If not, then "default/path" will be used as a default. 
                                            //Expressing a default value (delimited by colon ":") is optional. If no default is specified and 
                                            //a property cannot be resolved, an IllegalArgumentException will be thrown.
public class ProperySourcesConfig {

    /**
     * Property placeholder configurer needed to process @Value annotations
     */
     @Bean
     public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
     }
     
     
     
     
}
