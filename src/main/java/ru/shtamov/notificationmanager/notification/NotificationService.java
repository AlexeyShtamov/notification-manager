package ru.shtamov.notificationmanager.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.shtamov.notificationmanager.notification.converter.NotificationConverter;

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

    public List<EventNotification> getAllUnread(String login){

        List<EventNotification> eventNotifications = notificationRepository
                .findAll().stream()
                .filter(n -> n.getSubscribersLogins().contains(login))
                .map(notificationConverter::toDomain)
                .toList();

        log.info("Получен список всех уведомлений для пользователя: {}", login);

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
