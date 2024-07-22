# Dice Game - David Carcelen

## Description

This project is a dice game developed with Spring Boot, using JWT for user authentication and authorization. The game allows users to register, log in, and play dice. Additionally, there are user and admin roles that control access to certain endpoints.

## Features

- Authentication and authorization with JWT.
- User and admin roles.
- CRUD operations for players and games.
- Integration with MySQL and MongoDB databases.

## Prerequisites

- Java 17 or higher.
- Maven or Gradle.
- MySQL.
- MongoDB.

## Configuration

### Environment Variables

Set the following environment variables in your `application.properties` or `application.yml`:

```properties
spring.application.name=DiceGameCarcelenDavid

# SQL connection properties
spring.datasource.url=jdbc:mysql://localhost:3306/diceGame
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# MongoDB connection properties
spring.data.mongodb.uri=mongodb://localhost:27017/diceGame

# JWT secret key
jwt.secret=YourSecretKeyHere

# Server configuration
server.port=9006

# Admin credentials
admin.email=admin@admin.com
admin.password=admin
