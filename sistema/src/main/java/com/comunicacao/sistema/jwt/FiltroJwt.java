package com.comunicacao.sistema.jwt;

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
        // Permite acesso ao login sem autenticação JWT
        if (request.getRequestURI().startsWith("/auth/login") || request.getRequestURI().startsWith("/swagger-ui")) {
            filterChain.doFilter(request, response); // Permite acesso sem autenticação
            return;
        }

        // Aqui, o filtro JWT será aplicado às requisições com token JWT no cabeçalho
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // Remove o prefixo "Bearer "
            Claims claims = validadorJwt.validarToken(token);
            if (claims != null) {
                // Se o token for válido, configura o contexto de segurança
                String username = claims.getSubject();
                String role = claims.get("role").toString();  // A role aqui
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.createAuthorityList(role));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response); // Continua o fluxo da requisição
    }
}
