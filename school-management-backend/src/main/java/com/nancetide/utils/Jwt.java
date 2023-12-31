package com.nancetide.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class Jwt {

    static private final String signKey = "B1p87gOWQ736zJOADgAG6sfj1qruwKd6O6PjJ0AXYkrIcjkwr5iWtvjb4WlcAB9j";
    static private final Integer expire = 3 * 60 * (60 * 1000);

    @SuppressWarnings("all")
    static public String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    @SuppressWarnings("all")
    static public Claims decodeJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    static public String jwtToUsername(String jwt) {
        Claims claims = Jwt.decodeJwt(jwt);
        return (String) claims.get("username");
    }

    static public Short jwtToAccess(String jwt) {
        Claims claims = Jwt.decodeJwt(jwt);
        Double access = (Double) claims.get("access");
        return access.shortValue();
    }

}
