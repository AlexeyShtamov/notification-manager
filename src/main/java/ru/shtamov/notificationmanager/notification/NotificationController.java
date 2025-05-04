package ru.shtamov.notificationmanager.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shtamov.notificationmanager.config.security.jwt.JwtTokenManager;
import ru.shtamov.notificationmanager.notification.converter.NotificationDtoConverter;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationDtoConverter notificationDtoConverter;
    private final JwtTokenManager jwtTokenManager;

    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAllUnread(@RequestHeader("Authorization") String auth){
        String jwt = auth.substring(7);
        String login = jwtTokenManager.getLoginFromToken(jwt);

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        notificationService.getAllUnread(login).stream().map(notificationDtoConverter::toDto).toList()
                );
    }

    @PostMapping
    public ResponseEntity<Void> markAsRead(@RequestBody NotificationsIdDto notificationsIdDto){
        notificationService.markAsRead(notificationsIdDto.notificationIds());
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

}
