# Backend - Spring Boot Rental API

REST API for rental management built with Spring Boot 3.5.4, Spring Security, and MySQL.

## Key Features

-   JWT-based authentication
-   CORS configuration for frontend integration
-   File upload for rental images
-   Input validation and error handling
-   Database initialization with sample data
-   API documentation with Swagger

## Tech Stack

-   **Framework**: Spring Boot 3.5.4
-   **Security**: Spring Security with JWT authentication
-   **Database**: MySQL 8.0 with JPA/Hibernate
-   **Documentation**: OpenAPI 3 (Swagger)
-   **Build Tool**: Maven

## Project Structure

```
src/main/java/oc/rental/rental_oc/
├── config/          # Configuration classes (Security, CORS, JWT)
├── controller/      # REST controllers
├── dto/             # Data Transfer Objects
├── entity/          # JPA entities
├── exception/       # Custom exceptions
├── mapper/          # Entity-DTO mappers
├── repository/      # JPA repositories
└── service/         # Business logic
```

## Local Development

### Prerequisites

-   Java 17+
-   Maven 3.8+
-   Docker

### Database Setup

1. Activate Spring Docker Compose dependancy with property:

```properties
spring.docker.compose.enabled=true
```

2. Update `application.properties` for local development:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/oc_rental?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=rootpassword
```

### Running the Application

1. Install dependencies:

```bash
mvn clean install
```

2. Run the application:

```bash
mvn spring-boot:run
```

3. Application runs on: http://localhost:8080/api

### API Documentation

-   **Swagger UI**: http://localhost:8080/api/swagger
-   **OpenAPI JSON**: http://localhost:8080/api/v3/api-docs
