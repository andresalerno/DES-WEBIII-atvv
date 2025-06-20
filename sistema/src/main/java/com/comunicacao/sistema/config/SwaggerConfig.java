package com.comunicacao.sistema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    private static final String SCHEME_NAME = "BearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Microserviço de Autenticação").version("1.0"))
                .components(new Components()
                        .addSecuritySchemes(SCHEME_NAME,
                                new SecurityScheme()
                                        .name(SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                )
                .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME))
                .addServersItem(new io.swagger.v3.oas.models.servers.Server().url("http://localhost:8082").description("Microserviço de Autenticação"))
                .path("/auth/login", new io.swagger.v3.oas.models.PathItem()
                        .post(new io.swagger.v3.oas.models.Operation()
                                .summary("Login")
                                .description("Permite o login com credenciais para gerar um token JWT")
                                .operationId("login")
                        ));
    }
}
