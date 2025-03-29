package xyz.kajih.xplorer.mq.internal;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MQMessageListener {

    @JmsListener(destination = "${messaging.queue}", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(Message message) {
        try {
            if (message instanceof TextMessage textMessage) {
                String text = textMessage.getText();
                log.info("Received Message: {}", text);

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
        log.info("Processing: {}", text);
        try {
            Thread.sleep(5000); // Simulate processing delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("Processing Done: {}", text);
    }
}
