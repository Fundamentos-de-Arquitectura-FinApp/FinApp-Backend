package com.example.finappapirest.notifications.infraestructure.configuration.activemq.services;

import jakarta.jms.Topic;
import lombok.AllArgsConstructor;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageProducer {
    private JmsTemplate jmsTemplate;

    public void sendMessage(String destination, String message){
        Topic topic = new ActiveMQTopic(destination);
        jmsTemplate.convertAndSend(topic, message);
        System.out.println("Messaage sent to " + destination + " -> " + message);
    }
}
