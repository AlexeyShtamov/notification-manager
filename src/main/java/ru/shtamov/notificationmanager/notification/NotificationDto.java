package ru.shtamov.notificationmanager.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record NotificationDto(

        Long eventId,

        NotificationDto.FieldChangeDto<String> name,
        NotificationDto.FieldChangeDto<Integer> maxPlaces,
        NotificationDto.FieldChangeDto<OffsetDateTime> date,
        NotificationDto.FieldChangeDto<Integer> cost,
        NotificationDto.FieldChangeDto<Integer> duration,
        NotificationDto.FieldChangeDto<Long> locationId,
        NotificationDto.FieldChangeDto<String> status
) {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldChangeDto<T> {
        private T oldField;
        private T newField;

    }
}
