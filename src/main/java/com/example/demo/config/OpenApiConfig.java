package com.example.demo.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${spring.application.name}") String applicationName,
                                 @Value("${spring.application.version}") String version,
                                 @Value("${spring.application.author}") String author) {
        return new OpenAPI()
                .info(new Info()
                        .title(applicationName)
                        .version(version)
                        .license(new License().name(author)));
    }
}