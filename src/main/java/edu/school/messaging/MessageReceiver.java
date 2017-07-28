package edu.school.messaging;

 
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
 
 
@Component
public class MessageReceiver {
    static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
 
    private static final String ORDER_RESPONSE_QUEUE = "ORDER_QUEUE";
     
     
    @JmsListener(destination = ORDER_RESPONSE_QUEUE)
    public void receiveMessage(final Message message) throws JMSException {
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        MessageHeaders headers =  message.getHeaders();
        LOG.info("Application : headers received : {}", headers);
        System.out.println("已接受");
        Object response = message.getPayload();
        LOG.info("Application : response received : {}",response);
         
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}