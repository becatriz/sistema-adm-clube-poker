package com.clubepoker.service;

import com.clubepoker.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

   private static final Long TEMPO_EXPIRACAO = 18000000L;
   private String key = "mySecret2020";

    public String geraToken(Usuario usuario){

        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject("API Poker")
                .setExpiration(new Date(System.currentTimeMillis() + TEMPO_EXPIRACAO))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Claims decodeToken(String tokenTratado) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(tokenTratado).getBody();
    }
}
