Job Portal Backend API
A secure and scalable Job Portal backend application built using Spring Boot.
This project allows users to search and apply for jobs, HRs to post job openings, and manages authentication using JWT.
Features
User Registration & Login
HR Registration & Login
JWT Authentication & Authorization
Post and Manage Jobs
Apply for Jobs with Resume Upload
Save Jobs
View Applicants
RESTful APIs
DTO Architecture
Global Exception Handling
MySQL Database Integration
Tech Stack
Java 17
Spring Boot
Spring Data JPA
Spring Security
JWT
MySQL
Maven
Lombok
Modules
User Module
Register User
Login User
Update Profile
HR Module
Register HR
Login HR
Post Jobs
Manage Jobs
Job Module
Add Job
View All Jobs
Delete Job
Application Module
Apply for Jobs
Upload Resume
View Applicants
Saved Job Module
Save Jobs for Later
Database Configuration
Update application.properties
Properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobportal
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Authentication
JWT token is generated after successful login.
Example Response:
JSON
{
  "token":"eyJhbGciOiJIUzI1NiJ9..."
}
API Endpoints
User APIs
Method
Endpoint
POST
/UserCtrl/register
POST
/UserCtrl/login
GET
/UserCtrl/{id}
PUT
/UserCtrl/{id}
HR APIs
Method
Endpoint
POST
/HrCtrl/register
POST
/HrCtrl/login
PUT
/HrCtrl/{id}
Job APIs
Method
Endpoint
POST
/JobCtrl/add
GET
/JobCtrl/all
GET
/JobCtrl/{id}
DELETE
/JobCtrl/{id}
Run the Project
Bash
mvn spring-boot:run
Future Enhancements
Admin Dashboard
Email Notifications
Job Search Filters
Role-Based Access
Docker Deployment
Swagger Documentation
Author
Backend project developed using Spring Boot, JWT, and MySQL.
