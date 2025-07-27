package oc.rental.rental_oc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() throws IOException {
        ClassPathResource resource = new ClassPathResource("openapi.yaml");
        String content = Files.readString(resource.getFile().toPath());
        return new OpenAPIV3Parser().readContents(content).getOpenAPI();
    }
}