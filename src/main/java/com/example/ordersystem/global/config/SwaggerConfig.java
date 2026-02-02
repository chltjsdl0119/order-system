package com.example.ordersystem.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
         return new OpenAPI()
                 .info(apiInfo())
                 .servers(servers());
    }

    @Bean
    public GroupedOpenApi allApi() {
        return createGroup("All API", "/**");
    }

    @Bean
    public GroupedOpenApi productApi() {
        return createGroup("1. 상품(Product)", "/api/products/**");
    }

    private Info apiInfo() {
        return new Info()
                .title("Order System API Document")
                .description("Order System 서비스 API 명세서")
                .version("v1.0");
    }

    private List<Server> servers() {
        return List.of(
                new Server().url("http://localhost:8080").description("Local Server")
        );
    }

    private GroupedOpenApi createGroup(String groupName, String... paths) {
        return GroupedOpenApi.builder()
                .group(groupName)
                .pathsToMatch(paths)
                .build();
    }
}
