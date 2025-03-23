package xyz.kajih.xplorer.journal.internal.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.kajih.xplorer.journal.Journal;

@Component
public class JournalCmdLine implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(JournalCmdLine.class);
    private final Journal journal;

    public JournalCmdLine(Journal journal) {
        this.journal = journal;
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Journal  exists {}", journal.exists() ? "YES" : "NO");
        try (var buff = journal.reader()) {
            String line;
            while ((line = buff.readLine()) != null) {
                LOG.info("Processing Journal [{}]", line);
            }
        }
        journal.append("FOOOO");
        journal.append("BAR");
        journal.append("BAZ");
        journal.append("NEW");
    }
}
