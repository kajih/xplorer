package xyz.kajih.xplorer.mq.internal.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MqCmdLine implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(MqCmdLine.class);

    @Override
    public void run(String... args) throws Exception {
    }
}
