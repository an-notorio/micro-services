[Documentation](#microservices-demo-project) - [Technologies](#technologies-used) - [Roadmap](#future-releases) - [License](#license) 

# Microservices Demo Project

This repository contains a demo project showcasing a microservices-based application. The project consists of an API Gateway, Config Server, Discovery Server, and three microservices: Student, School and Authenticator.
With this project you can manage the school and student in a hypothetical region zone. With a role based access, this project use different technologies that will be explained in the next section.

Here's what the three microservices do specifically:

### Student Microservice

The Student Microservice is responsible for managing student-related data and operations, such as adding, updating, deleting and retrieving student records.

### School Microservice

The School Microservice manages school-related data and operations, including adding, updating, deleting and retrieving school records.

### Authenticator Microservice

The Authenticator service manages authentication and authorization along with login, registration, modification and deletion of users.

## Technologies Used

This project use Java LTS version 17 with Springboot 3.0.5.
In this project we use the convention over configuration states that supports the three main areas of MVC, namely models, views, and controllers. This means that if you establish
a set of naming conventions and the like, you can substantially reduce the amount of configuration needed to set up handler mappings, view resolvers, ModelAndView instances, etc.
This is a great advantage whent it comes to rapid prototyping and can also impart a degree of consistency (always useful) in a source code if you choose to proceed with production.
For more information about Spring Boot, you can visit the official sites [here](https://spring.io/projects/spring-boot).

For the Documentation, the project use [Swagger](http://swagger.io/). Using Swagger was an obvious choice for OpenAPI Specification: 

|   |          Feature          | Description |
|:-:|:-------------------------:| :----- |
| 1 | Streamline your Workflow  | Streamline API's build process by keeping its design, documentation, and implementation synchronized and updated automatically |
| 2 |   Restraint-Free Build    | Develop APIs in the technology stack of your choice with client libraries and server templates in every popular language |
| 3 | Open & Globally Supported | The OpenAPI Specification is a community-driven open specification within the Open API Initiative, a Linux Foundation Collaborative Project. |
| 4 |          Design           | OAS offers the complete format for designing APIs, defining resources and operations before writing code a line of code. |
| 5 |          Document           | Visualize your APIs operations and let internal developers and external consumers quickly adopt your API. |
| 6 |          Client & Server Generation           | Generate scaffolding for server stubs and client SDKs, based on your OAS definition |
| 7 |         Test          | Automate test case generation by defining a successful response in your OAS definition. |
| 8 |         Monitor          | Use the operations defined in your OAS generation to create API monitors. |
| 9 |         Deploy          |DeploySupported by leading API gateways like AWS, IBM, Apigee, and more. |


These are some feature of Swagger that make up with the decision to use it.

In the overview of using various tools in the project we decided to use [Spring Cloud](https://spring.io/projects/spring-cloud) (version 2022.0.2) which provides several tools to quickly build some of the common models in distributed systems.
Spring Cloud is an open source platform to help developers build distributed applications.
Spring Cloud provides tools for configuration management, service discovery, load balancing, circuit management, intelligent routing, bus control, one-time tokens, global locks, leadership election, sessions distributed and cluster state. 
In other words, Spring Cloud provides an easy way to implement common patterns in distributed systems, allowing developers to focus on the business logic of their applications.

#### Tools used from Spring Cloud:

- [Spring Cloud Config](https://spring.io/projects/spring-cloud-config)
- [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)
- [Spring Cloud Netflix](https://spring.io/projects/spring-cloud-netflix)
- [Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
- [Spring Cloud Sleuth](https://spring.io/projects/spring-cloud-sleuth)

#### Other tools used:

- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Framework Email](https://docs.spring.io/spring-framework/reference/integration/email.html)
- 

A small description of these tools will take place in the sections below. The official documentation can be found by clicking on them.

### API Gateway

The API Gateway serves as the single entry point for all client requests, managing and routing them to the appropriate microservices.

For the API gateway the project uses Spring Cloud Gateway.
Spring Cloud Gateway is a project that provides a library for building an API Gateway on Spring WebFlux or Spring WebMVC. 
It offers features such as security, monitoring, resiliency, and circuit breaker integration. 
Additionally, Spring Cloud Gateway is built on Spring Framework and Spring Boot, making it easy to use and customize. 
You can customize routes, use predicates and filters. 
Furthermore, Spring Cloud Gateway is highly scalable and can handle a large volume of traffic.

### Config Server

The Config Server centralizes configuration management for all microservices, simplifying application maintenance and consistency across environments.

Spring Cloud Config provides a way to manage external configuration in a distributed system. 
Spring Cloud Config allows you to manage external properties of applications in a centralized environment, providing an easy way to manage configuration across different environments.

### Discovery Server

The Discovery Server provides service registration and discovery, enabling seamless service-to-service communication within the microservices' ecosystem.

Spring Cloud Netflix is an open-source project that provides a set of tools for developing distributed applications based on Spring Boot. 
In particular, Spring Cloud Netflix offers an implementation of Netflix OSS (Open Source Software) for creating microservices applications.
Eureka is one of the core components of Spring Cloud Netflix. It is a Service Discovery service that allows microservices to register and discover other available services. 
In this way, microservices can communicate with each other without having to know the IP address or port of the other services in advance.
In summary, Spring Cloud Netflix simplifies the development of distributed microservices-based applications by providing tools for service management, load balancing, fault tolerance, security, and more.


## Inter-Service Communication

Internal communication between microservices is a fundamental aspect of microservices architecture. 
In a microservices architecture, services are designed to be autonomous and independent of each other. 
This means that services must be able to communicate with each other reliably and securely.

### Using OpenFeign

Spring Cloud OpenFeign provides a declarative REST client implementation for Spring Boot applications. 
Spring Cloud OpenFeign simplifies building REST clients for Spring Boot applications by providing an implementation of Netflix Feign.
With Spring Cloud OpenFeign, you can define a Java interface annotated with JAX-RS or Spring MVC annotations to define REST calls. 
Spring Cloud OpenFeign then creates a dynamic implementation of this interface, which can be used to make REST calls declarative.

## Distributed Tracing

Distributed tracing is a method used to profile or monitor the outcome of a request executed on a distributed system. 
In a microservices architecture, requests often involve multiple services, which makes it difficult to trace requests end-to-end to pinpoint the root causes of certain problems.
Distributed tracing allows you to monitor requests more effectively by providing a complete view of the flow of a request across all involved services. 
In practice, distributed tracking works by recording information about requests so that they can be followed across various services. 
This allows you to quickly identify any problems and resolve them in a timely manner.

### Spring Cloud Sleuth and Zipkin

The project showcases the use of Zipkin for distributed tracing, enhancing application observability and enabling the visualization and troubleshooting of latency issues.
Zipkin is an open-source distributed tracing system that helps solve latency issues in a microservices architecture. 
Zipkin collects timing data from applications and allows you to monitor the flow of a request across all involved services.
In practice, Zipkin works by recording information about requests so that they can be tracked across various services. 
This allows you to quickly identify any problems and resolve them in a timely manner.
Zipkin supports distributed tracking standards. In this way, it is possible to retrieve tracking information in messages exchanged within the microservices system.
To use Zipkin, applications must be “instrumented” to send tracking data to Zipkin. This can be done through the use of instrumentation libraries such as Spring Cloud Sleuth.

Spring Cloud Sleuth is an open-source project that provides a distributed tracing implementation for Spring Boot applications. 
Spring Cloud Sleuth simplifies the creation of tracing for Spring Boot applications by providing an implementation of Netflix OpenTracing.
With Spring Cloud Sleuth, you can generate trace data for HTTP requests automatically. 
In particular, it adds trace IDs and span IDs to HTTP requests so that they can be traced across the various services involved. 
In this way, it is possible to monitor the flow of a request through all the services involved and quickly identify any problems.

## Security

Security in microservices, as previously mentioned, is a fundamental aspect of microservices architecture. In a microservices architecture, services are designed to be autonomous and independent of each other. 
This means that each service must be protected independently of other services.
There are several security aspects that need to be considered in a microservices' architecture. Some of these include:
- Authentication: Authentication is the process of verifying the identity of a user or service. In a microservices architecture, authentication must be managed centrally to ensure that all services are uniformly protected.
- Authorization: Authorization is the process of determining whether a user or service has permission to access a resource or perform an action. In a microservices architecture, authorization must be managed centrally to ensure that all services are uniformly protected.
- Data Protection: Data protection is a critical aspect of security in a microservices' architecture. Data must be adequately protected during transmission and storage to avoid unauthorized access.
- Key Management: Key management is an important aspect of security in a microservices' architecture. Keys must be managed securely to avoid unauthorized access.
- Monitoring: Monitoring is a critical aspect of security in a microservices' architecture. Services must be continuously monitored to detect any suspicious activity or anomalies.
To ensure security in a microservices architecture, it is important to use security best practices and implement layered security measures. 
This includes using security tools like Spring Security.

### Spring Security and JWT

Spring Security is a security framework for Java applications. Provides authentication, authorization, and data protection capabilities for Java applications. 
In particular, Spring Security makes it easy to implement security features such as forms-based authentication, token-based authentication, and certificate-based authentication.

JSON Web Token (JWT) is an open standard for creating JSON-based security tokens. JWT tokens are used to authenticate and authorize HTTP requests. 
In practice, a JWT token contains information about the user or service that made the request and the permissions associated with that user or service.
Spring Security supports the use of JWT tokens for authenticating and authorizing HTTP requests. 
In particular, Spring Security offers a JWT implementation based on Spring Security JWT. With Spring Security JWT, you can generate and verify JWT tokens securely and reliably.
In summary, Spring Security and JWT are two technologies that can be used together to provide advanced security features to Java applications.

The project uses token-based authentication and therefore chose the JSON Web Token (JWT).

There are several reasons why it is convenient to use JWT. First, JWT is an open and widely used standard, meaning it is supported by many development libraries and frameworks. 
Furthermore, JWT is easy to implement and use, and is compatible with many platforms and programming languages.
JWT offers several advantages over other types of tokens. 
For example, is self-contained, meaning it contains all the information needed to verify the authenticity of the token. Is easy to transport and can be used in different contexts, such as HTTP requests.
In summary, JWT is one of the most used token types supported by development libraries. It offers several advantages over other types of tokens and is easy to implement and use.

Furthermore, the project uses a refresh token system, therefore dividing the token into two types: the access token and the refresh token.

#### The access token is used to access protected resources. 
The access token is a security token that is issued by the authorization server and contains information about the user or service who made the request and the permissions associated with that user or service. 
The access token has a limited duration and must be renewed periodically.
#### The refresh token is used to obtain a new access token when the current access token expires. 
The refresh token is a security token that is issued together with the access token and has a longer duration than the access token. 
When the access token expires, the client can use the refresh token to obtain a new access token without requiring the user to authenticate again.
In summary, the access token is used to access protected resources, while the refresh token is used to obtain a new access token when the current access token expires. 
Using a refresh token improves the user experience, as it avoids the need to authenticate repeatedly.

### Login System

The project's login system allows user registration with confirmation via email and the possibility of requesting a password reset if the user has forgotten the password.
It also has a soft ban system after multiple login attempts.

### Email System

For managed every email process, the project use spring mail with a configuration of SMTP server.

Spring Mail is a Spring Framework module that provides an abstraction for sending emails. 
In particular, Spring Mail simplifies sending emails by providing a high-level interface for sending emails and managing low-level resources such as connection pooling and mail server configuration.
Spring Mail supports sending emails via the SMTP protocol.
Sending emails via Simple Mail Transfer Protocol (SMTP) is a process in which the email client connects to the sender's SMTP server and sends the email message to the server. 
The sender's SMTP server then forwards the message to the recipient's SMTP server, which in turn forwards it to the recipient's mailbox.
To send an email via SMTP, you need to know the SMTP server address of the sender and recipient, as well as the sender's authentication credentials. 
Furthermore, it is necessary to use an email client that supports the SMTP protocol, in this project Gmail, Google's email service, is used.

## Future Releases

In the future there will be new implementations in the project including:

- #### Load Balancing System

*the load balancer is a technology used to distribute the workload between different servers or applications, improving system performance, scalability and availability.*

- #### Circuit breaker

*The circuit breaker monitors the flow of traffic between services and stops requests to services that do not respond or that respond slowly or with errors. 
When the circuit breaker interrupts a request, it returns an error to the client, indicating that the service is unavailable. 
In this way, the circuit breaker prevents system failure and ensures that client requests are handled efficiently.*

- #### Code improvement

*Being the first project we are working on, there are some parts of the code to refine or rewrite for a cleaner code. 
In the future there will be improvements, new features and faster systems. 
We will strive to keep the project updated and to the best of our ability.*

## License

This project is licensed under the [MIT License](LICENSE.txt).