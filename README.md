
---

# JWT Authentication Example in Spring Boot

This project demonstrates how to implement JWT-based authentication in a Spring Boot application. It includes user registration, login, and JWT token generation using Spring Security, and uses an in-memory database for testing purposes.

## Table of Contents
1. [Overview](#overview)
2. [Architecture](#architecture)
3. [JWT Token Flow](#jwt-token-flow)
4. [Setup](#setup)
5. [API Endpoints](#api-endpoints)
6. [Security Configurations](#security-configurations)
7. [License](#license)

## Overview

The application includes the following key features:
- User registration and authentication.
- JWT token generation after successful login.
- Secured API endpoints requiring valid JWT tokens for access.
- Stateless authentication using `Spring Security` and `JWT`.

## Architecture

The architecture of this application includes:
- **Spring Security**: Handles authentication and authorization.
- **JWT (JSON Web Tokens)**: Used to secure REST API endpoints.
- **Spring Boot**: The framework for building this application.
- **User Service**: Manages user operations like fetching user details and encoding passwords.
- **JwtHelper**: Handles JWT generation and validation.

### Flow Diagram
```
1. User logs in -> 2. Spring Security authenticates -> 3. JWT is generated -> 4. Token sent back to client -> 5. Subsequent requests use JWT
```

## JWT Token Flow

1. **Login Request**: A user sends a `POST` request to the `/auth/login` endpoint with their email and password.
2. **Authentication**: Spring Security’s `AuthenticationManager` validates the user's credentials.
   - If valid, the authentication is successful.
   - If invalid, the user receives an `Unauthorized` error response.
3. **JWT Generation**: Once the user is authenticated, the system generates a JWT token using the `JwtHelper` class.
4. **Token Response**: The JWT token is returned to the user in the response body.
5. **Authorization**: For future requests, the client must include this token in the `Authorization` header (prefixed with `Bearer`).
6. **Token Validation**: Each secured API call checks if the JWT token is valid and not expired using the `JwtAuthenticationFilter`.

### Key Components
- **JwtHelper**: Contains methods to create, validate, and extract claims from the JWT.
- **JwtAuthenticationFilter**: Extracts the JWT from the request, validates it, and sets the authentication in the `SecurityContextHolder`.
- **UserService**: Implements `UserDetailsService` to load user-specific data for authentication.

## Setup

### Prerequisites
- Java 17+
- Maven 3.x
- Spring Boot 3.x

### Steps to Run the Application
1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/jwt-spring-boot-example.git
   ```
2. Navigate into the project directory:
   ```bash
   cd jwt-spring-boot-example
   ```
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Configuration
The secret key for JWT is stored in the `JwtHelper` class. You can modify it according to your application's security requirements.

## API Endpoints

### 1. **User Registration**
   - **POST** `/auth/users`
   - Registers a new user with a hashed password using the `BCryptPasswordEncoder`.

### 2. **User Login (JWT Token Generation)**
   - **POST** `/auth/login`
   - Takes an email and password as input, authenticates the user, and returns a JWT token.

   **Request:**
   ```json
   {
       "email": "user@example.com",
       "password": "password123"
   }
   ```

   **Response:**
   ```json
   {
       "jwtToken": "eyJhbGciOiJIUzI1NiIsInR5c...",
       "username": "user@example.com"
   }
   ```

### 3. **Secure API Call (Requires JWT Token)**
   - **GET** `/api/secure-endpoint`
   - This endpoint is secured and requires a valid JWT token in the `Authorization` header.

   **Authorization Header Example:**
   ```
   Authorization: Bearer <JWT Token>
   ```

## Security Configurations

The security configuration (`SecurityConfig.java`) disables CSRF and CORS, permits all requests to `/auth/**`, and secures other endpoints.

- **JWT Authentication Filter**: The `JwtAuthenticationFilter` intercepts incoming requests, extracts the JWT from the `Authorization` header, validates it, and then sets the `Authentication` in the `SecurityContext`.
- **Exception Handling**: The `JwtAuthenticationEntryPoint` handles unauthorized access attempts by returning a 401 status.

## License

This project is licensed under the MIT License.

---

This README file provides an in-depth explanation of the JWT token creation and authentication process in your Spring Boot application.
