package ru.shtamov.notificationmanager.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenManager jwtTokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
        String authorizationHeaders = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeaders == null || !authorizationHeaders.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authorizationHeaders.substring(7);

        String roleFromJwt;
        try {
            roleFromJwt = jwtTokenManager.getRoleFromToken(jwt);
        } catch (Exception e){
            log.info("Ошибка при расшифровке jwt");
            filterChain.doFilter(request, response);
            return;
        }


        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                null,
                null,
                List.of(new SimpleGrantedAuthority(roleFromJwt))
        );

        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request, response);
    }

}
