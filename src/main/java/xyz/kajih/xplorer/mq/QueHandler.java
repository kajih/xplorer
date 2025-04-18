package xyz.kajih.xplorer.mq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueHandler {

    private final JmsTemplate jmsTemplate;

    public QueHandler(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void enqueue(String test) {
        jmsTemplate.convertAndSend("DEV.QUEUE.1", "CommandLine Message");
    }
}
