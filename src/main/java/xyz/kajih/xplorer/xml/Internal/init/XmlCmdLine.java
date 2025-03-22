package xyz.kajih.xplorer.xml.Internal.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class XmlCmdLine implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(XmlCmdLine.class);

    @Override
    public void run(String... args) throws Exception {
    }
}
