package com.comunicacao.sistema.config;

import com.comunicacao.sistema.jwt.FiltroJwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final FiltroJwt filtroJwt;

    // Injeção do filtro JWT
    public SecurityConfig(FiltroJwt filtroJwt) {
        this.filtroJwt = filtroJwt;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**", "/swagger-ui/**", "/auth/login").permitAll() // Permite o acesso ao Swagger sem autenticação
            .antMatchers("/**").hasRole("ADMIN")
            .antMatchers("/auth/login").permitAll()  // Permite o acesso ao endpoint de login sem autenticação
            .anyRequest().authenticated()  // Exige autenticação para outros endpoints
            .and()
            .addFilterBefore(filtroJwt, UsernamePasswordAuthenticationFilter.class)  // Adiciona o filtro JWT antes do filtro de autenticação
            .cors(); // Habilita CORS para o Swagger

    }

    // Configuração do AuthenticationManager
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Configura o serviço de autenticação em memória (apenas exemplo, você pode integrar com o seu banco)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN")
            .and()
            .withUser("user").password(passwordEncoder().encode("user123")).roles("USER");
    }

    // Configuração do PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Criação de um PasswordEncoder usando BCrypt
    }

    // Configuração do CORS para permitir acesso do Swagger na porta 8081
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // Permite o Swagger UI rodando na porta 8081
        config.addAllowedMethod("*"); // Permite todos os métodos (GET, POST, etc.)
        config.addAllowedHeader("*"); // Permite todos os cabeçalhos
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
