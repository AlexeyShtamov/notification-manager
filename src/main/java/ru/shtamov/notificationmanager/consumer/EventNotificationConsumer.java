package ru.shtamov.notificationmanager.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.shtamov.notificationmanager.notification.EventNotification;
import ru.shtamov.notificationmanager.notification.NotificationService;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventNotificationConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "notification-topic", containerFactory = "containerFactory")
    public void listenEvents(
            ConsumerRecord<Long, EventNotification> record
    ) {
        EventNotification eventNotification = record.value();
        log.info("Получена нотификация для мероприятия {}", eventNotification.getName());
        notificationService.saveNotification(eventNotification);
    }

}
