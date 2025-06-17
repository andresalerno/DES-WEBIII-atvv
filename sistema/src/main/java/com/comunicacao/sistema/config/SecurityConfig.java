package com.comunicacao.sistema.config;

import com.comunicacao.sistema.jwt.FiltroJwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final FiltroJwt filtroJwt;

    public SecurityConfig(FiltroJwt filtroJwt) {
        this.filtroJwt = filtroJwt;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/auth/login").permitAll()  // Permite o login sem autenticação
            .antMatchers("/usuarios").hasRole("USER") // Protege o endpoint de usuários
            .anyRequest().authenticated()
            .and()
            .addFilter(filtroJwt)  // Adiciona o filtro para validar o JWT
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // Sem sessões
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
