package com.product.application.config;

import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
//JWT token fabrikasi ya`ni boshqaruvchisi
public class JwtTokenUtil {

    private final String jwtSecret = "sgok2nsljd8Fbvnm5Adldxjkbs9Wvnjcb";
    private final String jwtIssuer = "iSystem.uz";

    public String generateAccessToken(UserDetails userDetails) {
        CustomUserDetails user = (CustomUserDetails) userDetails;

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setId("some id");
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.setSubject(String.format("%s,%s", user.getId(), userDetails.getUsername()));
        jwtBuilder.signWith(SignatureAlgorithm.HS256, jwtSecret);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)));
        jwtBuilder.setIssuer(jwtIssuer);
        return jwtBuilder.compact();
    }

    public String generateAccessToken(Integer id, String username) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setId("some id");
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.setSubject(String.format("%s,%s", id, username));
        jwtBuilder.signWith(SignatureAlgorithm.HS256, jwtSecret);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)));
        jwtBuilder.setIssuer(jwtIssuer);
        return jwtBuilder.compact();
    }

    public String getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[0];
    }

    public String getUserName(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
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
        } catch (SignatureException ex) {
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
