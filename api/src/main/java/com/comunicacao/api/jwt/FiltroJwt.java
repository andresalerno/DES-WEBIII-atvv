package com.comunicacao.api.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class FiltroJwt extends OncePerRequestFilter {

    private final ValidadorJwt validadorJwt;

    public FiltroJwt(ValidadorJwt validadorJwt) {
        this.validadorJwt = validadorJwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // Remove o prefixo "Bearer"
            Claims claims = validadorJwt.validarToken(token);

            if (claims != null) {
                String username = claims.getSubject();
                String role = (String) claims.get("role");  // Atribui a role

                // Evita a criação de um novo ciclo de autenticação
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authenticationToken = 
                        new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.createAuthorityList(role));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
