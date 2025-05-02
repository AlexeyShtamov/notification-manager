package ru.shtamov.notificationmanager.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.shtamov.notificationmanager.notification.converter.NotificationConverter;
import ru.shtamov.notificationmanager.user.User;
import ru.shtamov.notificationmanager.user.UserService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationConverter notificationConverter;


    public void saveNotification(EventNotification eventNotification){
        notificationRepository.save(notificationConverter.toEntity(eventNotification));
    }

    public List<EventNotification> getAllUnread(User user){
        List<EventNotification> eventNotifications = notificationRepository
                .findAll().stream()
                .filter(n -> n.getSubscribersLogins().contains(user.getLogin()))
                .map(notificationConverter::toDomain)
                .toList();

        log.info("Получен список всех уведомлений для пользователя: {}", user.getLogin());

        return eventNotifications;
    }

    public void markAsRead(List<Long> ids) {
        notificationRepository.findAllByIdIn(ids).forEach(e -> {
            e.setIsRead(true);
            notificationRepository.save(e);
        });

        log.info("Нотификации с id: {} помечены как прочитанные", ids);
    }
}
