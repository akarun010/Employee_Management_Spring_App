# Employee Management REST API

A Spring Boot REST API for managing employee information using **Spring Data JPA, MySQL, and RESTful web services**.

## 🚀 Features

* Create employees
* View all employees
* View an employee by ID
* Update employee details
* Delete employees
* Find employees by department
* Find employees based on minimum salary
* MySQL database integration
* RESTful API architecture
* Layered architecture using Controller, Service, and Repository

## 🛠️ Technologies Used

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Postman
* IntelliJ IDEA

## 🏗️ Project Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
MySQL Database
```

### Layers

**Controller**

* Handles HTTP requests
* Defines REST API endpoints
* Uses annotations such as `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, `@PathVariable`, and `@RequestParam`

**Service**

* Contains application/business logic
* Communicates between Controller and Repository

**Repository**

* Uses Spring Data JPA
* Handles database operations through `JpaRepository`

**Model**

* Represents the Employee entity
* Mapped to the database using JPA

## 👨‍💼 Employee Fields

| Field      | Type   |
| ---------- | ------ |
| id         | int    |
| name       | String |
| email      | String |
| department | String |
| salary     | int    |

## 🔗 API Endpoints

### 1. Get All Employees

```http
GET /employees
```

Returns all employees.

---

### 2. Get Employee by ID

```http
GET /employees/{id}
```

Example:

```http
GET /employees/1
```

---

### 3. Get Employees by Department

```http
GET /employees/department/{department}
```

Example:

```http
GET /employees/department/IT
```

---

### 4. Get Employees by Minimum Salary

```http
GET /employees/salary?min=30000
```

Returns employees whose salary is greater than the specified minimum salary.

Example:

```http
GET /employees/salary?min=30000
```

---

### 5. Add Employee

```http
POST /employees
```

Example request body:

```json
{
  "id": 1,
  "name": "Arun",
  "email": "arun@gmail.com",
  "department": "IT",
  "salary": 40000
}
```

---

### 6. Update Employee

```http
PUT /employees/{id}
```

Example:

```http
PUT /employees/1
```

Request body:

```json
{
  "name": "Arun Kumar",
  "email": "arunkumar@gmail.com",
  "department": "Development",
  "salary": 45000
}
```

The employee ID is taken from the URL.

---

### 7. Delete Employee

```http
DELETE /employees/{id}
```

Example:

```http
DELETE /employees/1
```

## 🗄️ Database Configuration

The project uses MySQL.

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/Employee_Management
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Create the database before running the application:

```sql
CREATE DATABASE Employee_Management;
```

The employee table is created/updated automatically by Hibernate because `ddl-auto` is set to `update`.

## 🧪 API Testing

All REST API endpoints were tested using **Postman**.

Tested operations:

* GET all employees
* GET employee by ID
* POST employee
* PUT employee
* DELETE employee
* Department filtering
* Minimum salary filtering
* Non-existing employee ID
* Duplicate employee ID

## 📂 Project Structure

```text
Employee-Management
│
├── src
│   └── main
│       ├── java
│       │   └── com.arun.Employee.Management
│       │       ├── Controller
│       │       │   └── EmployeeController.java
│       │       │
│       │       ├── Service
│       │       │   └── EmployeeService.java
│       │       │
│       │       ├── DAO
│       │       │   └── EmployeeDAO.java
│       │       │
│       │       ├── Model
│       │       │   └── Employee.java
│       │       │
│       │       └── EmployeeManagementApplication.java
│       │
│       └── resources
│           └── application.properties
│
└── pom.xml
```

## ▶️ How to Run

1. Clone the repository.
2. Create the `Employee_Management` database in MySQL.
3. Update the MySQL username and password in `application.properties`.
4. Open the project in IntelliJ IDEA or another Java IDE.
5. Run `EmployeeManagementApplication`.
6. The application starts on:

```text
http://localhost:8080
```

7. Use Postman to test the API endpoints.

## 📚 Concepts Practiced

This project helped practice:

* Spring Boot
* Dependency Injection
* Spring MVC
* REST API
* Layered Architecture
* HTTP Methods
* `@RestController`
* `@GetMapping`
* `@PostMapping`
* `@PutMapping`
* `@DeleteMapping`
* `@PathVariable`
* `@RequestBody`
* `@RequestParam`
* Spring Data JPA
* JpaRepository
* Hibernate
* MySQL
* CRUD operations
* Java Streams
* Postman API testing

## ✅ Project Status

**Completed ✅**

This project was created as practical experience with Spring Boot REST APIs, Spring Data JPA, and MySQL.
