package com.example.Blogging_platform2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class OpenApiConfig {
    

    @Bean
    public OpenAPI blogPlatformOpenAPI() {
        return new OpenAPI()

                .info(new Info()
                        .title("Blogging Platform API")
                        .description(
                            "A comprehensive REST API for a blogging platform. " +
                            "This API provides endpoints for managing users, blog posts, comments, tags, and reviews. " +
                            "\n\n**Features:**\n" +
                            "- User management (registration, authentication)\n" +
                            "- Blog post CRUD operations with pagination and filtering\n" +
                            "- Comments on posts\n" +
                            "- Tag-based categorization\n" +
                            "- Post reviews and ratings\n" +
                            "\n\n**Technology Stack:**\n" +
                            "- Spring Boot 3.2.2\n" +
                            "- Java 21\n" +
                            "- PostgreSQL\n" +
                            "- GraphQL (available at /graphiql)"
                        )
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Blogging Platform Team")
                                .email("support@bloggingplatform.com")
                        )
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")
                        )
                )

                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development Server"),
                        new Server()
                                .url("http://localhost:8081")
                                .description("Test Server")
                ));
    }
}
