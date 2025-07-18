# Library Management System

This project is a simple Library Management System built using Spring Boot and Oracle Database.

## Features

- Manage Books, Authors, Categories, Languages, and Publishers.
- Manage Users and Members.
- Borrowing system with due and return dates.
- Role-based access control (Admin, Librarian, Staff).
- User authentication using Basic Auth.
- Validation for all DTOs.
- Logging user activities.
- Clean code with layered architecture.

## Technologies Used

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Oracle Database
- Lombok
- MapStruct
- Postman (for testing)

## Project Structure

- `entity/` → JPA Entities
- `dto/` → Data Transfer Objects
- `repository/` → Spring Data Repositories
- `service/` → Interfaces and Implementations
- `controller/` → REST Controllers
- `config/` → Spring Security and app configs

## Design Choices

- Used `@Builder`, `@AllArgsConstructor`, `@NoArgsConstructor`, `@Getter`, `@Setter` from Lombok to reduce boilerplate code.
- Used DTOs for all requests/responses to separate concerns and avoid exposing entities directly.
- Validation added to DTOs using javax validation annotations like `@NotBlank`, `@Size`, `@Email`.
- Role-based access control is done with Spring Security using Basic Authentication.
- Used Enum `BorrowStatus` to track the status of borrow requests and use Enum `Languages` to restrict  languages.
- Lazy loading is used for most associations to avoid performance issues.
- User actions are logged for accountability.

## How to Run

1. Create an Oracle database.
2. Run the provided SQL script (`library_schema.sql`) to create schema and seed sample data.
3. Update your `application.yml` with DB credentials.
4. Run the Spring Boot app.
5. Use Postman to test the API.

## Author
Abdurhaman Hussien
This project was built for Code81 .
