# Rental OC

A full-stack rental management application with Spring Boot backend and Angular frontend.

## Features

-   User authentication and registration
-   Rental property listings and details
-   Property image management
-   User profile management
-   Responsive web interface

## Tech Stack

-   **Backend**: Spring Boot 3.5.4, Spring Security, MySQL 8.0
-   **Frontend**: Angular 14, nginx
-   **Database**: MySQL with JPA/Hibernate
-   **Authentication**: JWT tokens
-   **Containerization**: Docker & Docker Compose

## Prerequisites

-   Git
-   Docker and Docker Compose

## Quick Start

1. Clone the repository:

```bash
git clone https://github.com/The-Great-Toad/OC-Java-Angular-P3.git
cd OC-Java-Angular-P3
```

2. Build and start all services:

```bash
docker-compose up --build -d
```

3. Wait for services to initialize (2-3 minutes for first build)

4. Verify all containers are running:

```bash
docker ps
```

5. Access the application: http://localhost

## Default Test Account

-   **Email**: owner1@example.com
-   **Password**: password

## Access Points

-   **Application**: http://localhost
-   **API Documentation**: http://localhost:8080/api/swagger
-   **Backend API**: http://localhost:8080/api

## Services Architecture

| Service  | Technology      | Port | Description     |
| -------- | --------------- | ---- | --------------- |
| frontend | Angular + nginx | 80   | Web application |
| backend  | Spring Boot     | 8080 | REST API        |
| mysql_db | MySQL 8.0       | 3307 | Database        |

## Stopping the Application

```bash
docker-compose down
```

## Development

For local development setup, see:

-   [Backend README](./backend/README.md) - Spring Boot development
-   [Frontend README](./frontend/README.md) - Angular development

## Troubleshooting

View logs for any service:

```bash
docker logs <container-name>
# Examples:
docker logs oc_rental_backend
docker logs oc_rental_frontend
docker logs oc_rentals_mysql
```
