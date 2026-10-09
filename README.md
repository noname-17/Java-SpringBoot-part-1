# Java Spring Boot – Student REST API

A beginner-friendly RESTful API built with **Spring Boot** that performs full CRUD operations on students, backed by a **PostgreSQL** database. This is the first part of my Spring Boot learning journey and covers the core building blocks of a layered REST application.

## Features

- Create, read, update (full and partial) and delete students
- Layered architecture: Controller → Service → Repository
- DTOs to separate API models from database entities
- Request validation with Bean Validation (`@Valid`)
- Object mapping with ModelMapper
- PostgreSQL persistence via Spring Data JPA / Hibernate
- Boilerplate reduction with Lombok

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Language |
| Spring Boot 4.1.1 | Framework |
| Spring Web MVC | REST controllers |
| Spring Data JPA | Database access |
| PostgreSQL | Database |
| Bean Validation | Input validation |
| ModelMapper 3.2.4 | Entity ↔ DTO mapping |
| Lombok | Less boilerplate |
| Maven (wrapper included) | Build tool |

## Project Structure

```
LearningRESTAPIs/
├── pom.xml
├── mvnw / mvnw.cmd
└── src/main/
    ├── java/com/codingshuttle/youtube/LearningRESTAPIs/
    │   ├── LearningRestapIsApplication.java   # Entry point
    │   ├── config/
    │   │   └── MapperConfig.java              # ModelMapper bean
    │   ├── controller/
    │   │   └── StudentController.java         # REST endpoints
    │   ├── DTO/
    │   │   ├── AddStudentRequestDto.java      # Request body (validated)
    │   │   └── StudentDto.java                # Response body
    │   ├── entity/
    │   │   └── student.java                   # JPA entity
    │   ├── repository/
    │   │   └── StudentRepository.java         # JpaRepository
    │   └── service/
    │       ├── StudentService.java            # Service interface
    │       └── impl/StudentServiceImpl.java   # Business logic
    └── resources/
        └── application.properties
```

## Prerequisites

- JDK 21 or higher
- PostgreSQL installed and running
- Git
- (Optional) Postman or cURL for testing

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/noname-17/Java-SpringBoot-part-1.git
cd Java-SpringBoot-part-1/LearningRESTAPIs
```

### 2. Create the database

```sql
CREATE DATABASE "StudentsDB";
```

### 3. Configure the database connection

Edit `src/main/resources/application.properties` with your own PostgreSQL credentials:

```properties
spring.application.name=LearningRESTAPIs

spring.datasource.url=jdbc:postgresql://localhost:5432/StudentsDB
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.servlet.context-path=/api
```

The `student` table is created automatically on first run (`ddl-auto=update`).

### 4. Run the application

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

The API will be available at **http://localhost:8080/api**.

## API Endpoints

Base URL: `http://localhost:8080/api/students`

| Method | Endpoint | Description | Success Response |
|---|---|---|---|
| `GET` | `/students` | Get all students | `200 OK` |
| `GET` | `/students/{id}` | Get a student by ID | `200 OK` |
| `POST` | `/students` | Create a new student | `201 Created` |
| `PUT` | `/students/{id}` | Replace a student's data | `200 OK` |
| `PATCH` | `/students/{id}` | Update specific fields (`name`, `email`) | `200 OK` |
| `DELETE` | `/students/{id}` | Delete a student | `204 No Content` |

### Request body (POST / PUT)

```json
{
  "name": "John Doe",
  "email": "john@example.com"
}
```

**Validation rules**

- `name`: required, 3–30 characters
- `email`: required, must be a valid email

### Response body

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com"
}
```

### Example requests

**Create a student**

```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com"}'
```

**Get all students**

```bash
curl http://localhost:8080/api/students
```

**Get one student**

```bash
curl http://localhost:8080/api/students/1
```

**Update a student (full)**

```bash
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"John Smith","email":"john.smith@example.com"}'
```

**Update a student (partial)**

```bash
curl -X PATCH http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{"email":"new.email@example.com"}'
```

**Delete a student**

```bash
curl -X DELETE http://localhost:8080/api/students/1
```

## What I Learned

- Building REST controllers with `@RestController` and `ResponseEntity`
- Structuring an app into controller, service and repository layers
- Using DTOs instead of exposing entities directly
- Validating input with `@Valid`, `@NotBlank`, `@Size` and `@Email`
- Mapping objects with ModelMapper
- Connecting Spring Boot to PostgreSQL with Spring Data JPA
- Implementing PUT vs PATCH semantics

## Roadmap

- [ ] Add a global exception handler (`@RestControllerAdvice`) so "not found" returns `404` instead of `500`
- [ ] Add `@Valid` to the PUT endpoint
- [ ] Add a unique constraint on student email
- [ ] Move database credentials to environment variables
- [ ] Add pagination and sorting
- [ ] Write unit and integration tests
- [ ] Add Swagger / OpenAPI documentation

## Author

**noname-17** – [GitHub](https://github.com/noname-17)

---

*Just started learning Spring Boot — feedback and suggestions are welcome!*
