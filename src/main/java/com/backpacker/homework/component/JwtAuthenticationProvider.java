package com.backpacker.homework.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class JwtAuthenticationProvider {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${backpacker.jwt.secret}")
    private String secretKey;

    private long tokenValidTime = 1000L * 60 * 60;

    // JWT 토큰 생성
    public String createToken(String userPk, List<String> roles){
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken){
        try{
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            if(!Objects.isNull(valueOperations.get(jwtToken))){
                return false;
            }

            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        }catch (Exception e) {
            return false;
        }
    }

}
