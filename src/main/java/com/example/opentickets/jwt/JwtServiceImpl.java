package com.example.opentickets.jwt;

import com.example.opentickets.models.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private static final String ENC_KEY = "2A472D4B6150645367556B58703273357638792F423F4528482B4D6251655468";

    @Override
    public String generateToken(UserModel userModel) {
        return Jwts.builder()
                .signWith(getSigninKey())
                .setSubject(userModel.getEmail())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(100)))
                .claim("userRole", userModel.getUserRole())
                .compact();
    }

    @Override
    public UserModel parseToken(String jwt) {
        return null;
    }


    private Key getSigninKey() {
        byte[] keyBytes = Base64.getDecoder().decode(ENC_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
