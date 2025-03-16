package xyz.kajih.xplorer.product;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import xyz.kajih.xplorer.notification.NotificationDTO;
import xyz.kajih.xplorer.notification.NotificationService;

import java.util.Date;

/**
 * The Product Service
 */
@Service
public class ProductService {

    private final NotificationService notificationService;
    private final ApplicationEventPublisher events;

    /**
     * Instantiates.
     *
     * @param events              the events
     * @param notificationService the notification service
     */
    public ProductService(ApplicationEventPublisher events, NotificationService notificationService) {
        this.events = events;
        this.notificationService = notificationService;
    }

    /**
     * Create Product
     *
     * @param name        the producer name
     * @param productName the product name
     * @param price       the price
     */
    public void create(String name, String productName, int price) {
        notificationService.createNotification(new NotificationDTO(new Date(), "Tweet", productName));
    }
}
