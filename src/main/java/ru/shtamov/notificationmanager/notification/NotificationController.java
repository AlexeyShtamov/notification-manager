package ru.shtamov.notificationmanager.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shtamov.notificationmanager.config.security.jwt.JwtTokenFilter;
import ru.shtamov.notificationmanager.notification.converter.NotificationDtoConverter;
import ru.shtamov.notificationmanager.user.User;
import ru.shtamov.notificationmanager.user.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;
    private final NotificationDtoConverter notificationDtoConverter;
    private final JwtTokenFilter jwtTokenFilter;

    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAllUnread(@RequestHeader("Authorization") String auth){
        User user = userService.findByLogin(jwtTokenFilter.getLoginFromToken(auth));
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        notificationService.getAllUnread(user).stream().map(notificationDtoConverter::toDto).toList()
                );
    }

    @PostMapping
    public ResponseEntity<Void> markAsRead(@RequestHeader("Authorization") String auth, @RequestBody NotificationsIdDto notificationsIdDto){
        userService.findByLogin(jwtTokenFilter.getLoginFromToken(auth));
        notificationService.markAsRead(notificationsIdDto.notificationIds());
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

}
