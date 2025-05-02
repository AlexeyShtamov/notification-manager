package ru.shtamov.notificationmanager.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.shtamov.notificationmanager.exception.CustomAuthenticationEntryPoint;
import ru.shtamov.notificationmanager.user.User;
import ru.shtamov.notificationmanager.user.UserService;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter {

    private final JwtTokenManager jwtTokenManager;

    public String getLoginFromToken(String authorizationHeaders){

        if (authorizationHeaders == null || !authorizationHeaders.startsWith("Bearer "))
            throw new CustomAuthenticationEntryPoint("Необходима аутентификация с jwt токеном");

        String jwt = authorizationHeaders.substring(7);

        String loginFromJwt;
        try {
            loginFromJwt = jwtTokenManager.getLoginFromToken(jwt);
        } catch (Exception e){
            log.info("Ошибка при расшифровке jwt");
            throw new RuntimeException("Ошибка при расшифровке jwt");
        }

        return loginFromJwt;
    }
}
