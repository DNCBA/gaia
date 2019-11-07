package com.mhc.springboot.common.utlis;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-11-04 18:02
 */
public class JWTUtils {


    private static String secretKey = "qwer7qrasf";

    private JWTUtils() {
    }

    /**
     * 加密
     */
    public static String encode(String subject, Map<String, Object> claims) {
        LocalDateTime issueTime = LocalDateTime.now();
        Date.from(issueTime.toInstant(ZoneOffset.UTC));
        LocalDateTime expirationTime = issueTime.plusSeconds(60 * 60 * 1);
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Date.from(issueTime.toInstant(ZoneOffset.UTC)))
                .setExpiration(Date.from(expirationTime.toInstant(ZoneOffset.UTC)))
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    /**
     * 解密
     */
    public static Map<String, Object> decode(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }


}
