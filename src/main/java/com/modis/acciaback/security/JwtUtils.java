package com.modis.acciaback.security;

import com.modis.acciaback.security.data.CustomUserDetails;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.HashMap;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    private static String jwtSecret = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
    private static int jwtExpirationMs = 60 * 60 * 1000;

    public String generateJwtToken(Authentication authentication) {
        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();

        HashMap<String, String> claims = new HashMap<>();
        claims.put("firstName", userPrincipal.getUser().getPrenom());
        claims.put("lastName", userPrincipal.getUser().getNom());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {

        }
        return false;
    }
}
