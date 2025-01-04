# Project and Employee Management System

## Overview

This project is a Spring Boot-based application designed for managing employees, their project allocations, and roles within those projects. It includes functionality for assigning and unassigning employees from projects, querying employees based on various filters, and supporting sorting and pagination.

---

## Features

- **Employee Management**: 
  - Create, update, and delete employee records.
- **Project Allocation**: 
  - Assign and unassign employees from projects.
- **Search and Filters**: 
  - Filter employees based on various parameters such as skills, project name, and more.
- **Sorting and Pagination**: 
  - Sort employees based on experience or other criteria and paginate results.
- **Error Handling**: 
  - Handle missing employees, projects, and other potential errors gracefully.

---

## Technologies Used

- **Spring Boot**: For building the RESTful API.
- **MongoDB**: For data persistence (or any other database, depending on configuration).
- **Swagger**: For API documentation and testing.

---

## Prerequisites

Before getting started, ensure you have the following installed:

- **JDK 8** or later
- **Maven** or **Gradle**
- **Spring Boot 2.x** or later
- **MongoDB** (or another database, depending on configuration)

---

## Getting Started

### 1. Clone the Repository

Clone this repository to your local machine:

```bash
git clone https://github.com/lognathp/altass.git
```
### 2. Set Up Database
```bash
spring.data.mongodb.uri=mongodb://localhost:27017/projectEmployeeDB
```
### 3. Build the Project
```bash
mvn clean install
```
### 4. Run the Project
```bash
mvn spring-boot:run
```

### 5. API Documentation

For a detailed list of available APIs and request/response formats, please visit the Swagger UI. After running the application, navigate to the following URL in your browser:
```bash
[git clone https://github.com/lognathp/altass.git](http://localhost:8080/swagger-ui.html)
```

### 5. QueryFilter
  -  The QueryFilter class is used to build flexible queries for filtering employee data from the database.
  -  It allows filtering by various fields like primarySkill, secondarySkill, and projectName, as well as sorting and pagination options.
```bash
Example: To fetch Second most experience employee
{
  "filters": {
    "projectName": "EXPERIAN PROJECT"
  },
  "sortField": "overallExperience",
  "descending": true,
  "limit": 1,
  "skip": 1
}
```
### 8. Error Handling
   -  BadRequestException: Triggered if you try to assign an employee to more than 3 projects.
	 -  ResourceNotFoundException: Triggered if a project is not found in the employee’s allocation list.
### 9. Testing the API
   -  Use Postman or curl to test the API endpoints. Alternatively, on local setup up use swagger.
