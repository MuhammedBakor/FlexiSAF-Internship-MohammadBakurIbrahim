# Week 3 - Framework Architecture & REST Endpoints


## 🎯 Task
- Create a Spring Boot Application with atleast 4 endpoints.

---
## Summary of Changes
This week, I added:
- usage the `UserRepository` (Persistence Layer)
- `UserService` (Service Layer)
- `UserController` (Controller Layer)
- 4 REST endpoints for `UserData` (POST, GET, UPDATE, DELETE)

---

## 📖 Description
In this week, I implemented a **Spring Boot application** following the layered architecture approach with 4 REST endpoints.  
The project is structured into three main layers:
- **Persistence Layer:** Responsible for database operations using JPA repositories.
- **Service Layer:** Contains the business logic between the persistence and the controllers.
- **Controller Layer:** Exposes RESTful endpoints that can be consumed by clients.

---
## Endpoints
The application provides **4 REST endpoints** for the `UserData` entity:

- **POST api/v1/users** → Create a new user
- **GET api/v1/users/{id}** → Retrieve a single user by ID
- **UPDATE api/v1/user/{id}** → Update user by ID
- **DELETE api/v1/users/{id}** → Delete a user by ID

## Note:
- I haven't yet implemented any validations for the endpoints, you can only create, read, update and delete user as the following examples in Postman:

---

### (For the POST HTTP Endpoint):
### <br> write in JSON body:

      {
      "full_name": "Naser Noor",
      "email": "ns@gmail.com",
      "password": "123321123",
      "gender": "MALE",
      "date_of_birth": "1999-01-01",
      "role_name": "TEACHER"
       }


#### This is a UserRegistrationRequest class; it will be mapped to UserData entity then inserted into the database

---

### For the GET HTTP Endpoint:
#### <br> send: http://localhost:8080/api/v1/users/1
#### <br> the response will be:

     {
        "id": 1,
        "full_name": "Naser Noor",
        "email": "ns@gmail.com",
        "gender": "MALE",
        "date_of_birth": "1999-01-01",
        "roles": [
             "TEACHER"
         ]
     }


#### This is a UserDTO class, it is UserData entity converted into UserDTO, so we can show only the data we want the user to see. 

---

### For the PUT HTTP Endpoint:
####  <br> send: http://localhost:8080/api/v1/users/1
####  <br> write in JSON body:

     {
         "full_name": "Naser Nelson",
         "email": "nn@gmail.com",
         "password": "002002002",
         "date_of_birth": "2000-02-06"
     }

#### <br> and send another GET Request: http://localhost:8080/api/v1/users/1
#### <br> the response will be:
     {
         "id": 1,
         "full_name": "Naser Nelson",
         "email": "nn@gmail.com",
         "gender": "MALE",
         "date_of_birth": "2000-02-06"
         "roles": [
             "TEACHER"
         ]
     }


#### This is sending a UserUpdateRequest class, which will be updated the UserData entity.

---

### For the DELETE HTTP Endpoint:
### <br> send: http://localhost:8080/api/v1/users/1

#### This will delete the user with id [1]. To make sure the user is deleted, resend HTTP GET request [http://localhost:8080/api/v1/users/1], you will get 500 Internal Server Error, this is because I did not yet handled any exceptions, I will do that in the following tasks.

---


## 🚀 How to Run

---
1. Clone the repository
   ```bash
   git clone https://github.com/MuhammedBakor/FlexiSAF-Internship-MohammadBakurIbrahim.git

2. Navigate to week2

   ```bash
   cd week3/backend

3. Make sure PostgreSQL is running and update application.yaml with your DB credentials.


4. Run the app

   ```bash
   mvn spring-boot:run

5. Test the endpoints using Postman or browser:

 - http://localhost:8080/api/v1/users
---

## 📚 Learning Outcome
- Understood the layered architecture of Spring Boot (Persistence, Service, Controller).

- Learned how to design and expose RESTful endpoints.

- Gained hands-on practice with CRUD operations using Spring Data JPA

- **In this week, I extended the project by adding the following components:**
 
--- 

## What I Added New

---
In this week, I extended the project by adding the following components:

1. I used the **UserRepository (Persistence Layer):**
   - A JPA repository interface to handle database operations for the `UserData` entity.

2. I added **UserService (Service Layer):**
   - A service class that contains the business logic for managing users (CRUD operations).

3. I added **UserController (Controller Layer):**
   - A REST controller exposing endpoints for interacting with `UserData` with 4 **Endpoints:**
   
4. I added a userRequest class as (DTO) to transfer inserting new user data into UserData entity
   
5. I added new RoleRepository
  
   - `POST api/v1/users` → Create a new user
   - `GET api/v1/users/{id}` → Retrieve a user by ID
   - `UPDATE api/v1/users` → Update a user data
   - `DELETE api/v1/users/{id}` → Delete a user

