package ru.shtamov.notificationmanager.user;

import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User toDomain(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getLogin(),
                userEntity.getAge(),
                UserRole.valueOf(userEntity.getUserRole())
        );
    }
}
