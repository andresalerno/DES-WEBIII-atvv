package com.comunicacao.api.configuracao;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String SCHEME_NAME = "BearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Microserviço de API de Negócios").version("1.0"))
                .components(new Components()
                        .addSecuritySchemes(SCHEME_NAME,
                                new SecurityScheme()
                                        .name(SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                )
                .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME)) // Aplica segurança para todos os endpoints
                .addServersItem(new io.swagger.v3.oas.models.servers.Server().url("http://localhost:8081").description("Microserviço API de Negócios"));
    }
}
