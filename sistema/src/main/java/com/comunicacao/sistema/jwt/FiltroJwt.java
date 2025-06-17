package com.comunicacao.sistema.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class FiltroJwt extends OncePerRequestFilter {

    private final ValidadorJwt validadorJwt;

    public FiltroJwt(ValidadorJwt validadorJwt) {
        this.validadorJwt = validadorJwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // Remove o prefixo "Bearer "
            Claims claims = validadorJwt.validarToken(token);
            if (claims != null) {
                // Defina as informações do usuário no contexto de segurança
                request.setAttribute("username", claims.getSubject());
                request.setAttribute("role", claims.get("role"));
            }
        }

        filterChain.doFilter(request, response);
    }
}
