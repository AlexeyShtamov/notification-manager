package ru.shtamov.notificationmanager.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;


    public User findByLogin(String login){
        UserEntity userEntity = userRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("Нет пользователя с логином: " + login));

        log.info("Найден пользователь с логином: {}", login);

        return userConverter.toDomain(userEntity);
    }
}
