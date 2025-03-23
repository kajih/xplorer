package xyz.kajih.xplorer.mq.internal.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import xyz.kajih.xplorer.journal.Journal;

@Async
@EnableJms
@Component
public class MqCmdLine implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(MqCmdLine.class);
    private final JmsTemplate jmsTemplate;
    private final Journal journal;

    public MqCmdLine(JmsTemplate jmsTemplate, Journal journal) {
        this.jmsTemplate = jmsTemplate;
        this.journal = journal;
    }

    @Override
    public void run(String... args) throws Exception {
        jmsTemplate.convertAndSend("DEV.QUEUE.1", "CommandLine Message");

        try (var buff = journal.reader()) {
            String line;
            while ((line = buff.readLine()) != null) {
                LOG.info("Queueing Journal [{}]", line);
                jmsTemplate.convertAndSend("DEV.QUEUE.1", line);
            }
        }
    }
}
