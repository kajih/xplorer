package xyz.kajih.xplorer.notification.internal;

import java.util.Date;

public record Notification(Date date, NotificationType notificationType, String name) {
}
