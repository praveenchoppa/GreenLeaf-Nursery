# Nursery Management System

A full-stack Nursery Management Web Application built using Spring Boot, MySQL, HTML, CSS, and JavaScript. The project provides an admin dashboard for managing nursery products and categories with secure backend architecture, REST APIs, and Cloudinary image upload integration.

---

## Features

- Admin dashboard for nursery management
- Product and category CRUD operations
- RESTful API development using Spring Boot
- Image upload and management using Cloudinary
- Responsive frontend UI
- Structured layered backend architecture
- MySQL database integration
- Exception handling for clean API responses

---

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Hibernate

### Frontend
- HTML
- CSS
- JavaScript

### Database
- MySQL

### Tools & Services
- Cloudinary
- Maven
- Git
- Postman

---

## Project Structure

```bash
src/main/java/com/nursery
│
├── controller
├── service
├── repository
├── dto
├── entity
├── exception
├── security
└── config
```

---

## Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL

---

## Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/nursery-management-system.git
cd nursery-management-system
```

---

## Configure Database

Update the `application.properties` file:

```properties
spring.datasource.url=YOUR_DB_URL
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD

cloudinary.cloud-name=YOUR_CLOUD_NAME
cloudinary.api-key=YOUR_API_KEY
cloudinary.api-secret=YOUR_API_SECRET
```

---

## Run the Application

```bash
mvn spring-boot:run
```

---

## Features

- Admin dashboard for nursery management
- Product and category CRUD operations
- RESTful API development using Spring Boot
- JWT-based authentication and authorization
- Secure role-based access control
- Image upload and management using Cloudinary
- Responsive frontend UI
- Structured layered backend architecture
- MySQL database integration
- Exception handling for clean API responses

## Author

Choppa Praveen Nooka Vinay Kumar
