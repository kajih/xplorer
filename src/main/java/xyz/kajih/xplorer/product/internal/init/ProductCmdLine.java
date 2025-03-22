package xyz.kajih.xplorer.product.internal.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductCmdLine implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(ProductCmdLine.class);

    @Override
    public void run(String... args) throws Exception {
    }
}
