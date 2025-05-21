package com.ekkohno.anrex_back.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("Anrex API's").description("Anrex E-commerce Application APIs")
                        .version("1.0")
                        .contact(new Contact()
                                .name("ekkohno")));
    }
}