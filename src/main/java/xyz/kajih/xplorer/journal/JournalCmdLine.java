package xyz.kajih.xplorer.journal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JournalCmdLine implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        var j = new Journal("Journal");
        j.append("FOOOO");
        j.append("BAR");
        j.append("BAZ");

        System.out.println(j.exists() ? "YES" : "NO");
    }
}
