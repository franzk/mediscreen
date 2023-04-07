package com.abernathy.mediscreen.mgateway.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import com.abernathy.mediscreen.mgateway.exception.*;

@Service
@Log4j2
public class JwtService {

    @Value("${jwt-secret}")
    private String secret;

    public void validateToken(String token) throws ExpiredTokenException {
        log.info("Validating token : " + token);
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        } catch (ExpiredJwtException ex) {
            log.info("Token Expired");
            throw new ExpiredTokenException();
        }

    }

    public Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
