package com.perfulandia.logisticaservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api Logistica")
                        .version("1.0.0")
                        .description("Documentacion de la API Logistica para la tienda de perfulandia")
                        .contact(new Contact()
                                .name("Cristobal, Luis, Rodrigo")
                                .email("cr.valdebenitop@duocuc.cl")
                                .url("https://duoc.cl")

                        )
                );
    }
}
