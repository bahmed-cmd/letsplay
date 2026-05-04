#  Let's Play API

A secure and scalable RESTful CRUD API built with **Spring Boot** and **MongoDB**.  
The system manages users and products with JWT-based authentication and role-based access control.

---


- **Java 17**
- **Spring Boot 3.5.14**
- **Spring Security**
- **Spring Data MongoDB**
- **JWT (jjwt 0.12.6)**
- **Lombok**
- **Maven**

---

## Project Structure

```
src/main/java/com/letsplay/
├── controller        # REST Controllers
├── model             # MongoDB Entities
├── repository        # MongoDB Repositories
├── service           # Business Logic
├── security/         # Spring Security Configuration
│   └── jwt/          # JWT Filter & Utils
├── dto               # Data Transfer Objects
└── exception         # Global Exception Handler
```

---

## Prerequisites

Make sure you have the following installed:

- [Java 17](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/download.cgi)
- [MongoDB 7+](https://www.mongodb.com/try/download/community)

---

##  Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/bahmed-cmd/letsplay.git
cd letsplay
```

### 2. Start MongoDB

Make sure MongoDB is running on `localhost:27017`.

### 3. Configure the application

Edit `src/main/resources/application.properties` if needed:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/letsplay
jwt.secret=letsplay_super_secret_key_that_is_long_enough_for_hs256
jwt.expiration=86400000
server.port=8080
```

### 4. Run the application

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

---

##  Authentication

This API uses **JWT Bearer Token** authentication.

1. Register or login to receive a token
2. Include the token in the `Authorization` header:

```
Authorization: Bearer <your_token>
```

---

##  API Endpoints

### Auth

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| POST | `/api/auth/register` | Public | Register a new user |
| POST | `/api/auth/login` | Public | Login and get JWT token |

### Products

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| GET | `/api/products` | Public | Get all products |
| POST | `/api/products` | Authenticated | Create a new product |
| PUT | `/api/products/{id}` | Owner or Admin | Update a product |
| DELETE | `/api/products/{id}` | Owner or Admin | Delete a product |

### Users

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| GET | `/api/users` | Admin only | Get all users |
| GET | `/api/users/{id}` | Admin only | Get user by ID |
| DELETE | `/api/users/{id}` | Admin only | Delete a user |

---

##  Request & Response Examples

### Register
**POST** `/api/auth/register`
```json
{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123"
}
```
**Response:**
```json
{
    "token": "eyJhbGci...",
    "role": "USER"
}
```

### Login
**POST** `/api/auth/login`
```json
{
    "email": "john@example.com",
    "password": "password123"
}
```
**Response:**
```json
{
    "token": "eyJhbGci...",
    "role": "USER"
}
```

### Create Product
**POST** `/api/products`
```json
{
    "name": "Gaming Mouse",
    "description": "A high precision gaming mouse",
    "price": 49.99
}
```
**Response:**
```json
{
    "id": "64f9...",
    "name": "Gaming Mouse",
    "description": "A high precision gaming mouse",
    "price": 49.99,
    "userId": "64f8..."
}
```

---

##  Security Features

- Passwords hashed with **BCrypt**
- **JWT** tokens for stateless authentication
- **Role-based access control** (ADMIN / USER)
- Sensitive fields (password) excluded from responses
- Global exception handler — no unhandled 5XX errors

---

##  HTTP Status Codes

| Code | Meaning |
|------|---------|
| 200 | OK |
| 201 | Created |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 409 | Conflict (e.g. email already exists) |
| 500 | Internal Server Error |

---

##  Roles

| Role | Permissions |
|------|-------------|
| USER | Create, update, delete own products |
| ADMIN | Manage all users and products |

---

##  Author

**bahmed-cmd** — [GitHub](https://github.com/bahmed-cmd)
