                                         Notes Sharing Application (Backend-Only)

A secure backend application for creating, managing, and sharing notes, built with Spring Boot, Spring Security (JWT), and MySQL.

Tech Stack

    Java 8+
    Spring Boot (REST APIs, layered architecture)
    Spring Security + JWT
    JPA/Hibernate
    MySQL
    Postman (API Testing)
		
      
Features

-> User Authentication & Authorization

     Signup & Login with JWT-based authentication

     Passwords secured using BCrypt hashing

-> Notes Management (CRUD)

    Create, Read (with pagination & search), Update, Delete

    Notes are owner-specific (user sees only their notes)

-> Notes Sharing

    Share notes with other users

    Permissions: READ / WRITE

-> Security & Error Handling

    JWT-secured endpoints

    Global Exception Handling with structured API responses

    DTO-based request/response models for clean separation

-> Database

    MySQL integration with JPA/Hibernate

    Optimized queries with custom SQL/JPQL

-> Testing

    Endpoints tested using Postman

Project Structure

    src/main/java/com/nsa/base/notesSharingApplication
    ├── entity/             # Database models (User, Note, NoteShare)
    ├── dto/                # Request/Response payloads
    ├── repository/         # JPA repositories
    ├── service/            # Business logic
    ├── controller/         # REST APIs
    ├── security/           # JWT, filters, config
    └── exceptionHandler/   # Global exception handling







