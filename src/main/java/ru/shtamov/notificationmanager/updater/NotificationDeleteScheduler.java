package ru.shtamov.notificationmanager.updater;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.shtamov.notificationmanager.notification.NotificationRepository;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationDeleteScheduler {

    private final NotificationRepository notificationRepository;

    @Scheduled(cron = "${notification.delete.cron}")
    public void updateEventStatuses() {
        log.info("NotificationDeleteScheduler начал свою работу");

        int count = notificationRepository.deleteAllByIsReadIsTrue();

        log.info("Количество удаленных нотификаций: {}", count);
    }
}
