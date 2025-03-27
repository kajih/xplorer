package xyz.kajih.xplorer.journal.internal.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.kajih.xplorer.journal.Journal;

@Slf4j
@Component
public class JournalCmdLine implements CommandLineRunner {

    private final Journal journal;

    public JournalCmdLine(Journal journal) {
        this.journal = journal;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Journal  exists {}", journal.exists() ? "YES" : "NO");
        try (var buff = journal.reader()) {
            String line;
            while ((line = buff.readLine()) != null) {
                log.info("Processing Journal [{}]", line);
            }
        }
        journal.append("FOOOO");
        journal.append("BAR");
        journal.append("BAZ");
        journal.append("NEW");
    }
}
