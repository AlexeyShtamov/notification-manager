package ru.shtamov.notificationmanager.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventNotification {
    private Long eventId;
    private Long changerId;
    private Long ownerId;
    private List<String> subscribersLogins;

    FieldChange<String> name;
    FieldChange<Integer> maxPlaces;
    FieldChange<OffsetDateTime> date;
    FieldChange<Integer> cost;
    FieldChange<Integer> duration;
    FieldChange<Long> locationId;
    FieldChange<EventStatus> status;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldChange<T> {
        private T oldField;
        private T newField;

    }
}


