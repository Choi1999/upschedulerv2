package com.sparta.upschedulerv2.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component  // 필수
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    public String createToken(Long id,String username, String email, String role) {
        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .claim("username", username)
                .claim("email",email)// 이메일을 서브젝트로 설정
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    // JWT 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);  // 토큰 검증
            return true;
        } catch (Exception e) {
            // 토큰이 유효하지 않은 경우 예외 처리
            System.out.println("유효하지 않은 JWT 토큰: " + e.getMessage());
        }
        return false;
    }

    // Claims 추출
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // "Bearer " 제거
    public String substringToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        throw new IllegalArgumentException("잘못된 JWT 토큰 형식");
    }
}