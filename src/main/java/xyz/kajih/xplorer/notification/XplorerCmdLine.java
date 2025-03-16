package xyz.kajih.xplorer.notification;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import xyz.kajih.xplorer.product.ProductService;

@Component
public class XplorerCmdLine implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public XplorerCmdLine(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        this.applicationContext
                .getBean(ProductService.class)
                .create("baeldung", "course", 10);
    }
}
