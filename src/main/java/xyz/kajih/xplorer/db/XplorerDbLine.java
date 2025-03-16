package xyz.kajih.xplorer.db;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.kajih.xplorer.db.internal.ProductEntity;

@Component
public class XplorerDbLine implements CommandLineRunner {

    private final DbRepository dbRepository;

    public XplorerDbLine(DbRepository repository) {
        this.dbRepository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        dbRepository.save(new ProductEntity(null, "Jack", "Bauer"));
    }
}
