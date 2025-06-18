package com.comunicacao.sistema.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class GeradorJwt {

    // A chave secreta deve ter pelo menos 512 bits para usar o algoritmo HS512
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Gera a chave segura

    // Gerar o token JWT com a chave segura
    public String gerarToken(String username, String tipoUsuario) {
        // Define o tempo de expiração do token (1 hora)
        long expirationTime = 1000 * 60 * 60; // 1 hora em milissegundos
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        // Cria o JWT com a chave, username e tipo de usuário
        return Jwts.builder()
                .setSubject(username) // Definindo o username como o sujeito do token
                .claim("role", tipoUsuario) // Definindo o tipo de usuário como claim
                .setExpiration(expirationDate) // Definindo a data de expiração
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512) // Usando a chave segura com o algoritmo HS512
                .compact(); // Retorna o token compactado
    }
}
