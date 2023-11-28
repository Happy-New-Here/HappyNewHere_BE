package com.example.HappyNewHere.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

public class JwtUtils {
    //토큰 만료 여부 확인
    public static boolean isExpired(String token, String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    //토큰 생성
    public static String createJwt(Long accountId, String secretKey, Long expiredMs){
        Claims claims = Jwts.claims();
        claims.put("accountId",accountId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //멤버 Id 추출
    public static Long extractMember(String token, String secretKey){
        Long accountId = Long.parseLong(Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token).getBody()
                .get("accountId").toString());

        return accountId;
    }
}