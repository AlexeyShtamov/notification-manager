package ru.shtamov.notificationmanager.exception;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        String message,
        String detailMessage,
        LocalDateTime dateTime
) {
}
