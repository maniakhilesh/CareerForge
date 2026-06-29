# 🚀 CareerForge

### AI-Powered Career Management Platform

> **Enterprise-grade Spring Boot backend demonstrating secure authentication, scalable REST APIs, Dockerized deployment, Flyway database migrations, automated testing, and GitHub Actions CI/CD.**

**Java 21 • Spring Boot • PostgreSQL • JWT • Docker • Flyway • GitHub Actions • Swagger**


![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue)
![JWT](https://img.shields.io/badge/Security-JWT-red)
![Swagger](https://img.shields.io/badge/API-Swagger-success)
![Status](https://img.shields.io/badge/Status-Active-brightgreen)

---

## 📌 Overview

Job seekers often struggle to organize opportunities, track application statuses, and measure progress during their placement or job search journey.

CareerForge solves this problem by providing a secure backend platform that enables users to:

- Create and manage job opportunities
- Track applications throughout the hiring lifecycle
- Monitor interview progress
- View personalized dashboard analytics
- Access secure APIs using JWT authentication
- Explore and test APIs through Swagger UI

The project is designed using modern backend engineering practices commonly used in enterprise applications.

---
## ⭐ Engineering Highlights

- ✅ Enterprise Layered Architecture
- ✅ JWT Authentication & Spring Security
- ✅ PostgreSQL + Flyway Database Versioning
- ✅ Dockerized Deployment
- ✅ Automated CI/CD with GitHub Actions
- ✅ Integration Testing with Testcontainers
- ✅ REST API Documentation with Swagger

## ✨ Key Features

### 🔐 Authentication & Security

- User Registration
- User Login
- BCrypt Password Hashing
- JWT-Based Authentication
- Stateless Security Architecture
- Protected API Endpoints
- Custom Authentication Filter

### 💼 Opportunity Management

- Create Opportunities
- Retrieve Opportunities
- Delete Opportunities
- Pagination Support
- Future-ready filtering architecture

### 📋 Application Tracking

- Apply to Opportunities
- Track Application Status
- View User Applications
- Status Lifecycle Management

Supported statuses:

- APPLIED
- INTERVIEW_SCHEDULED
- OFFERS_RECEIVED
- REJECTED

### 📊 Dashboard Analytics

Provides real-time insights:

- Applications Applied
- Interviews Scheduled
- Offers Received
- Rejections
- Saved Opportunities

### ⚠️ Exception Handling

- Global Exception Handling
- Custom Business Exceptions
- Consistent Error Responses
- Validation Error Handling
- UUID Validation Support

### 📖 API Documentation

- Swagger/OpenAPI Integration
- Interactive API Testing
- Developer-Friendly Documentation

---

## 🏗️ Architecture

```text
                ┌─────────────────┐
                │     Client      │
                │ Postman / Web   │
                └────────┬────────┘
                         │
                         ▼
                ┌─────────────────┐
                │ Spring Boot API │
                └────────┬────────┘
                         │
         ┌───────────────┼────────────────┐
         ▼               ▼                ▼

 ┌────────────┐  ┌──────────────┐  ┌────────────┐
 │ Auth Layer │  │ Business API │  │ Dashboard  │
 └────────────┘  └──────────────┘  └────────────┘
         │
         ▼
 ┌─────────────────────┐
 │ Spring Security JWT │
 └─────────────────────┘
         │
         ▼
 ┌─────────────────────┐
 │ PostgreSQL Database │
 └─────────────────────┘
```

---

## 🛠️ Tech Stack

### Backend

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Hibernate

### Database

- PostgreSQL 17

### Security

- JWT Authentication
- BCrypt Password Encoding

### Documentation

- Swagger / OpenAPI

### Build Tool

- Maven

---

## 📂 Project Structure

```text
src/main/java/com/careerforge

├── auth
│   ├── controller
│   ├── dto
│   ├── security
│   └── service
│
├── application
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
│
├── opportunity
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
│
├── dashboard
│   ├── controller
│   └── service
│
├── user
│   ├── entity
│   └── repository
│
├── common
│   ├── exception
│   └── response
│
└── config
```

---

## 🔄 API Modules

### Authentication

```http
POST /api/auth/register
POST /api/auth/login
```

### Opportunities

```http
POST   /api/opportunities
GET    /api/opportunities
GET    /api/opportunities/{id}
DELETE /api/opportunities/{id}
```

### Applications

```http
POST /api/applications
GET  /api/applications/my
PATCH /api/applications/{id}/status
```

### Dashboard

```http
GET /api/dashboard/stats
```

---

## 📄 Sample Dashboard Response

```json
{
  "saved": 0,
  "applied": 12,
  "interviewScheduled": 4,
  "offersReceived": 2,
  "rejected": 3
}
```

---

## 🔍 Pagination Support

CareerForge supports enterprise-style pagination.

Example:

```http
GET /api/opportunities?page=0&size=10
```

Response:

```json
{
  "content": [...],
  "totalElements": 25,
  "totalPages": 3,
  "number": 0
}
```

---

## 🔐 Security Highlights

- Stateless JWT Authentication
- BCrypt Password Hashing
- Protected Endpoints
- Environment Variable Support
- Custom Security Filter Chain
- Unauthorized Access Protection

Sensitive credentials are externalized using environment variables:

```properties
spring.datasource.password=${DB_PASSWORD}
jwt.secret=${JWT_SECRET}
```

---

## 🚀 Running the Project

### Clone Repository

```bash
git clone https://github.com/maniakhilesh/CareerForge.git
cd careerforge-backend
```

### Configure Environment Variables

```bash
DB_PASSWORD=your_postgres_password
JWT_SECRET=your_jwt_secret
```

### Start Application

```bash
mvn spring-boot:run
```

Application starts at:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## 🎯 Engineering Highlights

This project demonstrates:

- REST API Design
- Secure Authentication & Authorization
- Layered Architecture
- Exception Handling
- Pagination
- Database Modeling
- API Documentation
- Enterprise Backend Development Practices

---

## 🔮 Future Enhancements

- Chrome Extension Integration
- Job Auto-Scraping
- Resume Analysis
- AI-Powered Job Matching
- Email Notifications
- Docker Deployment
- Cloud Deployment (AWS)
Role-Based Authorization (RBAC)
Redis Caching
Search & Filtering
AWS Deployment
Monitoring & Logging

---

## 👨‍💻 Author

Mani Akhilesh Kumar

Passionate about Backend Development, Java, Spring Boot, System Design, and building scalable software solutions.

---
⭐ If you found this project interesting, consider giving it a star.
