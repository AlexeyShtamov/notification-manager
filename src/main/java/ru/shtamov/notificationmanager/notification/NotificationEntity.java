package ru.shtamov.notificationmanager.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.OffsetDateTime;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;
    private Long changerId;
    private Long ownerId;

    @ElementCollection
    private List<String> subscribersLogins;

    private String oldName;
    private String newName;
    private Integer oldMaxPaces;
    private Integer newMaxPlaces;

    private OffsetDateTime oldDate;
    private OffsetDateTime newDate;
    private Integer oldCost;
    private Integer newCost;
    private Integer oldDuration;
    private Integer newDuration;
    private Long oldLocationId;
    private Long newLocationId;
    private String oldEventStatus;
    private String newEventStatus;

    private Boolean isRead;


}
