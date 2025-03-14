# Spring Security with JWT in Action

**Example of Spring Security implementation with JWT**

## Overview

The application is designed as a microservice with no user interface.

The app provides 3 methods to `register`, `login` and `getProfile`. By default, every new user is registered with the
`ROLE_USER` role. There is no function to change the role via API. When calling the login registration methods, the
response will return an `accessToken`.

In the current implementation, the data is stored in a separate Postgres schema `accounts` that provides a materialized
view `user_roles_m_view`. The schema contains 3 tables: `users`, `roles` and `user_roles`. By default the project
supports 4 starting roles: `ROLE_USER`, `ROLE_MANAGER`, `ROLE_ANALYTICS`, `ROLE_ADMIN`.

[API documentation](http://localhost:8080/swagger-ui/index.html#/) will be available once the application is started

## Technologies

- `Java` - version `17`
- `Maven` - for building the application
- `Spring Boot` - version `3.2.5`
- `JWT`
- `OpenAPI` - API documentation
- `Spring Boot Maven Plugin` - for create Docker-Image
- `Docker-Compose` - infrastructure
- `Postgres` - credential storage

## Structure of the project

```
spring-security-with-jwt-in-action/
├── src/main/
|   ├── java/com/dudko/example
|   |   ├── config/
|   |   ├── controller/             # controllers
|   |   ├── domain/                 # persistent domain level and repositories
|   |   ├── model/                  # service level of the domain, used in business logic and controllers
|   |   ├── security/               # security logic
|   |   ├── service/                # business logic
|   ├── resources/                  # configs, validation messages and schema for DB with initial scripts
├── pom.xml                         # artifact of Maven
├── compose.yml                     # docker-compose file
├── postman_collection.json         # collection of requests for Postman
```

## How to try this project?

Don't forget to set the JWT_SECRET environment variable before running the application (you know what to do).
You can use, for example, [this service](https://emn178.github.io/online-tools/sha256.html)

```sh
docker-compose -f compose.yml up
```

### Author:

Anatoly Dudko
