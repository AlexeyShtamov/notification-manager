package ru.shtamov.notificationmanager.notification;

import java.util.List;

public record NotificationsIdDto(
        List<Long> notificationIds
) {
}
