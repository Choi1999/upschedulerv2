package com.sparta.upschedulerv2.config;




import org.springframework.stereotype.Component;


import java.util.Date;


@Component
public class JwtUtil {

    private final String SECRET_KEY = "your-secret-key";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // JWT 토큰 생성
    public String createToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // JWT 토큰 검증
    public void validateToken(String token) throws JwtException {
        Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
    }

    // Claims 추출
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // "Bearer "를 제외한 실제 토큰 추출
    public String substringToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        throw new IllegalArgumentException("Invalid JWT token");
    }
}