package com.example.gateway;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Gateway", version = "1.0", description = "Documentation API Gateway v1.0 Microservices \n \nThis is a description of a microservices sample that uses swagger to document and test the APIs. The sample consists of three services: a school service, a student service, and an authenticator service. Each service has its own swagger file that defines the endpoints, parameters, responses, and schemas. The swagger files are exposed by the services and can be accessed through a tool like Postman.The Student service and the school service have a internal communication in a Http protocol. \n\n\n - [The Microservices repository](https://github.com/an-notorio/micro-services)", termsOfService = "https://choosealicense.com/licenses/mit/"), externalDocs = @ExternalDocumentation(description = "Find out more about Swagger", url = "http://swagger.io"))
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route(r -> r.path("/students/v3/api-docs").and().method(HttpMethod.GET).uri("lb://students"))
				.route(r -> r.path("/schools/v3/api-docs").and().method(HttpMethod.GET).uri("lb://schools"))
				.route(r -> r.path("/authenticator/v3/api-docs").and().method(HttpMethod.GET).uri("lb://authenticator"))
				.build();
	}

}
