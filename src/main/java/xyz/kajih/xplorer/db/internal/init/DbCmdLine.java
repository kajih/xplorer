package xyz.kajih.xplorer.db.internal.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.kajih.xplorer.db.DbRepository;
import xyz.kajih.xplorer.db.internal.ProductEntity;

@Component
public class DbCmdLine implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DbCmdLine.class);
    private final DbRepository dbRepository;

    public DbCmdLine(DbRepository repository) {
        this.dbRepository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        dbRepository.save(new ProductEntity(null, "Jack", "Bauer", 100));
    }
}
