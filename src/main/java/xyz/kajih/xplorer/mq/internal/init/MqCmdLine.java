package xyz.kajih.xplorer.mq.internal.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import xyz.kajih.xplorer.journal.Journal;

@Slf4j
@Profile("MQ")
@EnableJms
@Component
public class MqCmdLine implements CommandLineRunner {

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
                log.info("Queueing Journal [{}]", line);
                jmsTemplate.convertAndSend("DEV.QUEUE.1", line);
            }
        }
        log.info("Queueing done! --------------------------------------");
    }
}
