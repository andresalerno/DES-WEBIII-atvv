package com.comunicacao.sistema.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GeradorJwt {

    private String chaveSecreta = "minhaChaveSecreta"; // Defina uma chave secreta segura

    public String gerarToken(String username, String role) {
        Date agora = new Date();
        Date expirarEm = new Date(agora.getTime() + 86400000); // O token expira em 1 dia

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)  // Atribui a role ao JWT
                .setIssuedAt(agora)
                .setExpiration(expirarEm)
                .signWith(SignatureAlgorithm.HS512, chaveSecreta)
                .compact();
    }
}
