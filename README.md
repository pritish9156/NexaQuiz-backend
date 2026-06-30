# 🚀 NexaQuiz Backend

> Spring Boot Backend for **NexaQuiz – Training & Assessment Management System**

NexaQuiz Backend is a secure REST API built using **Spring Boot**, **Spring Security**, **JWT Authentication**, **Hibernate (JPA)**, and **MySQL**. It powers the complete Training & Assessment Management System by providing role-based authentication, quiz management, evaluation, and reporting functionalities.

---

# 📌 Features

## 🔐 Authentication

- JWT Authentication
- Role-Based Authorization
- Secure Login
- BCrypt Password Encryption
- Forgot Password
- Reset Password via Email

---

## 👨‍💼 Admin Features

- Dashboard Analytics
- Subject Management
- Topic Management
- Question Bank
- Student Management
- Trainer Management
- Batch Management
- Quiz Creation
- Quiz Publishing
- Result Management

---

## 👨‍🏫 Trainer Features

- Dashboard
- Subject Management
- Topic Management
- Question Management
- Quiz Management
- Result Viewing

---

## 👨‍🎓 Student Features

- Student Dashboard
- Available Quizzes
- Quiz Timer
- Quiz Submission
- Automatic Evaluation
- Quiz Review
- Quiz History
- Leaderboard
- Student Profile

---

# 🛠 Tech Stack

| Technology | Description |
|------------|-------------|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Secure Authentication |
| Hibernate (JPA) | ORM Framework |
| MySQL | Database |
| Maven | Dependency Management |
| Lombok | Boilerplate Reduction |
| Java Mail | Password Reset Emails |

---

# 🏗 Project Architecture

```
Controller
      │
      ▼
Service Layer
      │
      ▼
Repository Layer
      │
      ▼
MySQL Database
```

---

# 📂 Project Structure

```
src
 └── main
      ├── java
      │     └── com.backend.nexaquiz
      │            ├── config
      │            ├── controller
      │            ├── dto
      │            ├── entity
      │            ├── repository
      │            ├── security
      │            ├── service
      │            ├── exception
      │            └── util
      │
      └── resources
             ├── application.properties
             └── static
```

---

# 🗄 Database Tables

- Users
- Subjects
- Topics
- Questions
- Batches
- Batch Students
- Quizzes
- Quiz Questions
- Quiz Attempts
- Attempt Answers
- Password Reset Tokens

---

# 🔐 Security

✔ JWT Authentication

✔ Spring Security

✔ BCrypt Password Encryption

✔ Role-Based Authorization

✔ Protected REST APIs

---

# 📡 REST API Modules

### Authentication

- Login
- Forgot Password
- Reset Password

### Subjects

- Create Subject
- Update Subject
- Delete Subject
- View Subjects

### Topics

- CRUD Operations

### Questions

- CRUD Operations
- Difficulty Level
- Code Snippets

### Students

- CRUD Operations
- Batch Assignment

### Trainers

- CRUD Operations

### Batches

- CRUD Operations

### Quizzes

- Create Quiz
- Publish Quiz
- Schedule Quiz
- Assign Questions

### Results

- Student Results
- Review Answers
- Leaderboard

---

# 🚀 Getting Started

## Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/nexaquiz-backend.git
```

---

## Open Project

```bash
cd nexaquiz-backend
```

---

## Configure Database

Update **application.properties**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nexaquiz

spring.datasource.username=root

spring.datasource.password=your_password
```

---

## Run Application

```bash
mvn spring-boot:run
```

Application will start on

```
http://localhost:8080
```

---

# 📚 API Documentation

Example Login Endpoint

```
POST /api/auth/login
```

Example Quiz Endpoint

```
GET /api/student/quizzes
```

Example Result Endpoint

```
GET /api/results
```

---

# ✨ Highlights

- JWT Authentication
- Spring Security
- RESTful APIs
- Hibernate ORM
- Batch-wise Quiz Assignment
- Automatic Evaluation
- Leaderboard
- Responsive Architecture
- Production Ready Structure

---

# 📈 Future Enhancements

- AI Question Generation
- AI Performance Analysis
- Certificate Generation
- Excel Import/Export
- Email Notifications
- Analytics Dashboard
- Mobile Application

---

# 👨‍💻 Developer

**Pritish Pawar**

Java Full Stack Developer

GitHub: https://github.com/pritish9156

LinkedIn: www.linkedin.com/in/pritishpawar

---

# ⭐ If you found this project helpful

Please consider giving this repository a **Star ⭐**

It helps support the project and encourages future development.

---

## 📜 License

This project was developed as part of a **Java Full Stack Internship** for learning and portfolio purposes.
