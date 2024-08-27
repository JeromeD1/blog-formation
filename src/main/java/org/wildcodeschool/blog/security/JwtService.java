package org.wildcodeschool.blog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

//    @Value("${security.jwt.secret}")
//    private String jwtSecret;

    public String generateToken(UserDetails userDetails) {
//        System.out.println("userDetails: " + userDetails.getUsername());
//        System.out.println("authorities: " + userDetails.getAuthorities());
//        System.out.println("secretKey: " + secretKey);
//        System.out.println("jwtExpiration: " + jwtExpiration);
//        String jwt = Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .claim("roles", userDetails.getAuthorities())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//
//        System.out.println("jwt" + jwt);
//        return jwt;
        try {
            return Jwts.builder()
                    .setSubject(userDetails.getUsername())
                    .claim("roles", userDetails.getAuthorities())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la génération du token JWT", e);
        }
    }

    public Claims extractClaims(String token) {
        System.out.println("token: " + token);
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateJwtToken(String token) {
        try {
//            Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(token);
            Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
