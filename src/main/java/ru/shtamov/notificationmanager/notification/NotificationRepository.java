package ru.shtamov.notificationmanager.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    List<NotificationEntity> findAllByIdIn(List<Long> ids);

    int deleteAllByIsReadIsTrue();

}
