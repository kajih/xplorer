package xyz.kajih.xplorer.mq.internal;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MQMessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(MQMessageListener.class);

    @JmsListener(destination = "DEV.QUEUE.1", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(Message message) {
        try {
            if (message instanceof TextMessage textMessage) {
                String text = textMessage.getText();
                System.out.println("Received Message: " + text);

                // Process the message asynchronously
                processMessage(text);

                // Acknowledge the message
                message.acknowledge();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void processMessage(String text) {
        // Simulating processing
        System.out.println("Processing: " + text);
        try {
            Thread.sleep(1000); // Simulate processing delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Processing Done: " + text);
    }
}
