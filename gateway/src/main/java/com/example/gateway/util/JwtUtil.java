package com.example.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtUtil {


    @Value("${application.security.jwt.secret-key}")
    private String secretKey;


    public void validateToken(String token) {
        Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractClaims(token);
        List<String> roles = new ArrayList<String>();
        //Scorriamo tutti i ruoli che troviamo nei claim e utilizziamo substring per poterci tirare solo il ruolo per poi inserirlo in una nuova lista di String
        for(int i=0; i<claims.get("roles", List.class).size(); i++){
            roles.add(claims.get("roles", List.class).get(i).toString().substring(11,claims.get("roles", List.class).get(i).toString().length()-1));
        }
        return roles;
    }
}