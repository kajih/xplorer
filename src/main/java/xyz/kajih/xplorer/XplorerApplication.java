package xyz.kajih.xplorer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class XplorerApplication {

    private static final Logger LOG = LoggerFactory.getLogger(XplorerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(XplorerApplication.class, args);
    }
}
