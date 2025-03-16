package xyz.kajih.xplorer.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import xyz.kajih.xplorer.notification.internal.Notification;
import xyz.kajih.xplorer.notification.internal.NotificationType;

/**
 * The type Notification service.
 */
@Service
public class NotificationService {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationService.class);
    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * Instantiates a new Notification service.
     *
     * @param applicationEventPublisher the application event publisher
     */
    public NotificationService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * Publish notification.
     *
     * @param notification the notification
     */
    public void createNotification(NotificationDTO notification) {

        applicationEventPublisher.publishEvent(notification);

        LOG.info("Received notification by module dependency for product {} in date {} by {}.",
                notification.getProductName(),
                notification.getDate(),
                notification.getFormat());

    }

    /**
     * Notification event Listener.
     *
     * @param event the event
     */
    @EventListener
    public void notificationEvent(NotificationDTO event) {
        Notification notification = toEntity(event);
        LOG.info("Received notification by event for product {} in date {} by {}.",
                notification.date(),
                notification.notificationType(),
                notification.name());
    }

    private Notification toEntity(NotificationDTO event) {
        NotificationType format = NotificationType.UNKNOWN;
        try {
            format = NotificationType.valueOf(event.getFormat());
        } catch (IllegalArgumentException e) {
            LOG.error("Unknown notification format for product {} - {}", event.getProductName(), event.getFormat());
        }
        return new Notification(event.getDate(), format, event.getProductName());
    }
}