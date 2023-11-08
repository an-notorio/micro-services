# Microservices Demo Project

This repository contains a demo project showcasing a microservices-based application. The project consists of an API Gateway, Config Server, Discovery Server, and two microservices: Student and School.

### API Gateway

The API Gateway serves as the single entry point for all client requests, managing and routing them to the appropriate microservices.

### Config Server

The Config Server centralizes configuration management for all microservices, simplifying application maintenance and consistency across environments.

### Discovery Server

The Discovery Server provides service registration and discovery, enabling seamless service-to-service communication within the microservices ecosystem.

### Student Microservice

The Student Microservice is responsible for managing student-related data and operations, such as adding, updating, and retrieving student records.

### School Microservice

The School Microservice manages school-related data and operations, including adding, updating, and retrieving school records.

## Inter-Service Communication

### Using OpenFeign

This project demonstrates inter-service communication using OpenFeign, a declarative REST client that simplifies service-to-service communication within the microservices ecosystem.

## Distributed Tracing

### Using Zipkin

The project showcases the use of Zipkin for distributed tracing, enhancing application observability and enabling the visualization and troubleshooting of latency issues.

## Security

### Spring Security

The project use the JWT authentication method for access in secured endpoint using an access token and a resfresh token

### Login

The project allows the registration of a user with confirmation via email and the reset of the password in case it has been forgotten.

### Spring Mail

For managed every email process, the project use spring mail with a configuration of SMTP server. The provider used is Gmail.

## License

This project is licensed under the [MIT License](LICENSE.txt).