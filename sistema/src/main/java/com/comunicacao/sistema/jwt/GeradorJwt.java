package com.comunicacao.sistema.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.comunicacao.sistema.config.JwtConfig;
import com.comunicacao.sistema.enumeracoes.TipoUsuario;

import java.security.Key;
import java.util.Date;

@Component
public class GeradorJwt {

    @Autowired
    private JwtConfig jwtConfig;

    // Gerar o token JWT com a chave segura
    public String gerarToken(String username, TipoUsuario tipoUsuario) {
        // Define o tempo de expiração do token (1 hora)
        long expirationTime = 1000 * 60 * 60; // 1 hora em milissegundos
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        // Cria o JWT com a chave, username e tipo de usuário
        Key key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());  // Usando a chave da configuração

        return Jwts.builder()
                .setSubject(username) // Definindo o username como o sujeito do token
                .claim("role", tipoUsuario.name()) // Definindo o tipo de usuário como claim
                .setExpiration(expirationDate) // Definindo a data de expiração
                .signWith(key, SignatureAlgorithm.HS512) // Usando a chave segura com o algoritmo HS512
                .compact(); // Retorna o token compactado
    }
}
