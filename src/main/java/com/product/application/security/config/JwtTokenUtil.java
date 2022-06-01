package com.product.application.security.config;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Calendar;
import java.util.Date;

public class JwtTokenUtil {

    private final String jwtSecret = "gschmsdjhvlufxhvnxflnvhj";
    private final String jwtIssuer = "isystem.uz";

    public String generateAccessToken(UserDetails userDetails){
        CustomUserDetails user = (CustomUserDetails) userDetails;

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setId("Some id");
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.setSubject(String.format("%s,%s",user.getUsername(),user.getPassword()));
        jwtBuilder.signWith(SignatureAlgorithm.HS256,jwtSecret);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (24*60*60*1000)));
        jwtBuilder.setIssuer(jwtIssuer);

        String jwt = jwtBuilder.compact();
        return jwt;
    }

    public String generateAccessToken(String username,Integer id){
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setId("Some id");
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.setSubject(String.format("%s,%s",username,id));
        jwtBuilder.signWith(SignatureAlgorithm.HS256,jwtSecret);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (24*60*60*1000)));
        jwtBuilder.setIssuer(jwtIssuer);

        String jwt = jwtBuilder.compact();
        return jwt;
    }

    public String getUserId(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject().split(",")[0];
    }

    public String getUsername(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex) {
            // logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            // logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            // logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            // logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            // logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }
}
