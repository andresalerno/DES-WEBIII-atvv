package com.comunicacao.api.configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Configuração de segurança para a aplicação
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**", "/swagger-ui/**") // Permite o acesso público ao Swagger UI
            .permitAll() // Permite o acesso ao Swagger sem autenticação
            .anyRequest().authenticated() // Exige autenticação para outros endpoints
            .and()
            .formLogin().disable(); // Desabilita o login por formulário (se necessário)
    }

    // Configuração de autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user").password("{noop}password").roles("USER") // Usuário em memória com role USER
            .and()
            .withUser("admin").password("{noop}admin").roles("ADMIN"); // Usuário em memória com role ADMIN
    }
}
