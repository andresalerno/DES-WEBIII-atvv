package com.comunicacao.api.configuracao;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String SCHEME_NAME = "BearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("AutoManager API").version("1.0"))
                .components(new Components()
                        .addSecuritySchemes(SCHEME_NAME,
                                new SecurityScheme()
                                        .name(SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                )
                .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME))  // Adiciona a exigência do token Bearer
                .addServersItem(new io.swagger.v3.oas.models.servers.Server().url("http://localhost:8082"))
                .path("/auth/login", new io.swagger.v3.oas.models.PathItem()
                        .post(new io.swagger.v3.oas.models.Operation()
                                .summary("Login")
                                .description("Permite o login com credenciais para gerar um token JWT")
                                .operationId("login")
                                .addParametersItem(new Parameter()
                                        .name("username")
                                        .description("Nome de usuário")
                                        .required(true)
                                        .in("query")
                                        .schema(new StringSchema()))
                                .addParametersItem(new Parameter()
                                        .name("password")
                                        .description("Senha do usuário")
                                        .required(true)
                                        .in("query")
                                        .schema(new StringSchema()))
                                .responses(new io.swagger.v3.oas.models.responses.ApiResponses()
                                        .addApiResponse("200", new io.swagger.v3.oas.models.responses.ApiResponse()
                                                .description("Token JWT gerado com sucesso"))
                                        .addApiResponse("401", new io.swagger.v3.oas.models.responses.ApiResponse()
                                                .description("Usuário ou senha inválidos"))
                                )
                        ));
    }

}
