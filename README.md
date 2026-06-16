# 🌿 GreenLeaf Nursery Management System

A full-stack Nursery Management Web Application built using Spring Boot, MySQL, HTML, CSS, and JavaScript.

The application allows administrators to manage flower categories, flowers, images, and users through a secure dashboard while providing a responsive public-facing nursery showcase website for customers.

---

## ✨ Features

### Public Website

* Browse flower categories
* View flower details
* Search categories
* Responsive design for desktop and mobile
* Contact and WhatsApp integration

### User Features

* User Registration
* User Login
* JWT Authentication
* Profile Management
* Role-Based Authorization

### Admin Features

* Secure Admin Login
* Category CRUD Operations
* Flower CRUD Operations
* Cloudinary Image Upload
* Dashboard Management
* User Access Control

### Backend Features

* RESTful APIs
* Spring Security Integration
* JWT Authentication & Authorization
* Global Exception Handling
* DTO & Mapper Pattern
* Layered Architecture
* MySQL Database Integration

---

## 🛠 Tech Stack

### Backend

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* JWT Authentication

### Frontend

* HTML5
* CSS3
* JavaScript

### Database

* MySQL

### Tools & Services

* Cloudinary
* Maven
* Git
* GitHub
* Postman

---

## 🔐 Authentication

The system uses JWT-based authentication.

### Roles

#### ROLE_ADMIN

* Access Admin Dashboard
* Manage Categories
* Manage Flowers
* Upload Images
* Perform CRUD Operations

#### ROLE_USER

* Register Account
* Login
* View Profile
* Access Public Website

---

##  Getting Started

### Prerequisites

* Java 17+
* Maven
* MySQL
* Cloudinary Account

---

### Clone Repository

```bash
git clone https://github.com/praveenchoppa/GreenLeaf-Nursery.git
cd GreenLeaf-Nursery
```

---

### Configure Environment Variables

Create the following environment variables:

```properties
DB_URL=YOUR_DB_URL
DB_USERNAME=YOUR_DB_USERNAME
DB_PASSWORD=YOUR_DB_PASSWORD

CLOUDINARY_CLOUD_NAME=YOUR_CLOUD_NAME
CLOUDINARY_API_KEY=YOUR_API_KEY
CLOUDINARY_API_SECRET=YOUR_API_SECRET

JWT_SECRET=YOUR_JWT_SECRET
```

---

### Run Application

```bash
mvn spring-boot:run
```

Application runs at:

```text
http://localhost:8080
```

---

## 📸 Image Upload

Flower images are uploaded using Cloudinary and stored as secure URLs inside the database.

---

## 📡 API Highlights

### Authentication

```text
POST /api/auth/register
POST /api/auth/login
POST /api/auth/create-admin
```

### Categories

```text
GET    /api/categories
POST   /api/admin/categories
DELETE /api/admin/categories/{id}
```

### Flowers

```text
GET    /api/flowers
GET    /api/flowers/{id}
POST   /api/admin/flowers
PUT    /api/admin/flowers/{id}
DELETE /api/admin/flowers/{id}
```

### User

```text
GET /api/users/me
```

---

## 🎯 Future Enhancements

* Forgot Password
* Email Verification
* Order Management
* Shopping Cart
* Online Payments
* Inventory Tracking
* Deployment to Cloud

---

##  Author

**Choppa Praveen Nooka Vinay Kumar**

GitHub:
https://github.com/praveenchoppa
