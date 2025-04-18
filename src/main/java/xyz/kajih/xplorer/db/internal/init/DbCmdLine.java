package xyz.kajih.xplorer.db.internal.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.kajih.xplorer.db.DbRepository;
import xyz.kajih.xplorer.db.internal.ProductEntity;

@Slf4j
@Component
public class DbCmdLine implements CommandLineRunner {

    private final DbRepository dbRepository;

    public DbCmdLine(DbRepository repository) {
        this.dbRepository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        dbRepository.save(new ProductEntity(null, "Jack", "Bauer", 100));
    }
}
