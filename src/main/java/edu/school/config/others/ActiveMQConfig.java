package edu.school.config.others;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

@Configuration
//@EnableJms
public class ActiveMQConfig {

	@Value("${ActiveMQ.DEFAULT_BROKER_URL}")
    private String DEFAULT_BROKER_URL;
    
	@Value("${ActiveMQ.ORDER_QUEUE}")
    private String ORDER_QUEUE;
 
    @Bean
    public ActiveMQConnectionFactory mqConnectionFactory(){
        ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory();
        mqConnectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        //connectionFactory.setTrustedPackages(Arrays.asList("com.websystique.springmvc"));
        return mqConnectionFactory;
    }
    
    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(mqConnectionFactory());
        template.setDefaultDestinationName("ORDER_QUEUE");
        return template;
    }
	
    
    @Bean
    MessageConverter converter(){
        return new SimpleMessageConverter();
    }
}
