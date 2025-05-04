package ru.shtamov.notificationmanager.config.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenManager {

    private final Key key;


    public JwtTokenManager(@Value("${jwt.key}") String key) {
        this.key = Keys.hmacShaKeyFor(key.getBytes());
    }

    public String getRoleFromToken(String jwt){
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .get("role", String.class);
    }

    public String getLoginFromToken(String jwt) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .getSubject();
    }
}
