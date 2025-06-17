package com.comunicacao.sistema.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class ValidadorJwt {

    private String chaveSecreta = "minhaChaveSecreta"; // Defina uma chave secreta segura

    // Método para validar o token e obter as claims
    public Claims validarToken(String token) {
        try {
            // Criando uma chave secreta para validação
            Key chave = Keys.hmacShaKeyFor(chaveSecreta.getBytes());

            // Usando o JwtParserBuilder para validar o token
            return Jwts.parser()
                    .setSigningKey(chave) // Passa a chave para a validação
                    .build()
                    .parseClaimsJws(token)
                    .getBody(); // Retorna as claims contidas no token
        } catch (Exception e) {
            // Caso o token seja inválido ou expirado, retorna null
            return null;
        }
    }
}
