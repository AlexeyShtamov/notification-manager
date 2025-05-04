package ru.shtamov.notificationmanager.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Slf4j
@Component
public class CustomAccessDeniedHandler extends RuntimeException {
    public CustomAccessDeniedHandler(String message) {
        super(message);
    }
}
