package com.comunicacao.sistema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permite todas as origens de uma vez, mas pode ajustar para o seu frontend
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8081") // Permitindo requisições do microserviço API
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos permitidos
                .allowedHeaders("*") // Todos os cabeçalhos
                .allowCredentials(true);  // Permitindo enviar credenciais (como o token JWT)
    }
}
