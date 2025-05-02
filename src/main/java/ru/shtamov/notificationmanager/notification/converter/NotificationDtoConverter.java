package ru.shtamov.notificationmanager.notification.converter;

import org.springframework.stereotype.Component;
import ru.shtamov.notificationmanager.notification.EventNotification;
import ru.shtamov.notificationmanager.notification.NotificationDto;

@Component
public class NotificationDtoConverter {

    public NotificationDto toDto(EventNotification notification){
        return new NotificationDto(

                notification.getEventId(),
                notification.getName() != null ?
                new NotificationDto.FieldChangeDto<>(notification.getName().getOldField(), notification.getName().getNewField()) : null,
                notification.getMaxPlaces() != null ?
                new NotificationDto.FieldChangeDto<>(notification.getMaxPlaces().getOldField(), notification.getMaxPlaces().getNewField()) : null,
                notification.getDate() != null ?
                new NotificationDto.FieldChangeDto<>(notification.getDate().getOldField(), notification.getDate().getNewField()) : null,
                notification.getCost() != null ?
                new NotificationDto.FieldChangeDto<>(notification.getCost().getOldField(), notification.getCost().getNewField()) : null,
                notification.getDuration() != null ?
                new NotificationDto.FieldChangeDto<>(notification.getDuration().getOldField(), notification.getDuration().getNewField()) : null,
                notification.getLocationId() != null ?
                new NotificationDto.FieldChangeDto<>(notification.getLocationId().getOldField(), notification.getLocationId().getNewField()) : null,
                notification.getStatus() != null ?
                new NotificationDto.FieldChangeDto<>(notification.getStatus().getOldField().name(), notification.getStatus().getNewField().name()) : null

        );


    }
}
