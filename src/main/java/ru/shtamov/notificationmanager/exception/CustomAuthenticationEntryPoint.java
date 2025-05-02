package ru.shtamov.notificationmanager.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint extends RuntimeException {

    public CustomAuthenticationEntryPoint(String message) {
        super(message);
    }
}
