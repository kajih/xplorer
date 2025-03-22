package xyz.kajih.xplorer.journal.internal.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.kajih.xplorer.journal.Journal;

@Component
public class JournalCmdLine implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(JournalCmdLine.class);

    @Override
    public void run(String... args) throws Exception {
        Journal j = new Journal("Journal");
        LOG.info("Journal  exists {}", j.exists() ? "YES" : "NO");
        try (var buff = j.reader()) {
            String line;
            while ((line = buff.readLine()) != null) {
                LOG.info("Processing Journal [{}]", line);
            }
        }
        j.append("FOOOO");
        j.append("BAR");
        j.append("BAZ");
        j.append("NEW");
    }
}
