package com.example.school.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                description = "OpenApi documentation for Spring Security",
                title = "OpenApi specification",
                version = "1.0"
        ),
        servers =    {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8222"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "Bearer Authentication"
                )
        }
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
@Configuration
//@Profile({"local", "dev"})
public class OpenApiConfigs {
//    @Bean
//    public OpenAPI customOpenAPI(
//            @Value("schools doc") String serviceTitle,
//            @Value("1.0.0") String serviceVersion,
//            @Value("http://localhost:8222") String url) {
//        final String securitySchemeName = "bearerAuth";
//        return new OpenAPI()
//                .servers(List.of(new Server().url(url)))
//        .components(
//            new Components()
//                .addSecuritySchemes(
//                    securitySchemeName,
//                    new SecurityScheme()
//                        .type(SecurityScheme.Type.HTTP)
//                        .scheme("bearer")
//                        .bearerFormat("JWT")))
//        .security(List.of(new SecurityRequirement().addList(securitySchemeName)))
//                .info(new Info().title(serviceTitle).version(serviceVersion));
//    }
}
