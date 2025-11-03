# Week 9 – Integration & Unit Testing


## 🎯 Task

- Write test cases for previous projects


## 📖 Description
This week focused on implementing **Unit Tests** and improving code quality using Spring Boot testing capabilities, specifically:

✔ Mockito for mocking dependencies  
✔ MockMvc for testing REST API endpoints  
✔ Testing Service and Controller layers  
✔ Ensuring a clean architecture & separation of concerns  
✔ Testing success scenarios and response message validation

Unit testing helps detect issues early, improve confidence while refactoring, and ensure reliability of functionalities.

---

## ✅ What I Implemented

### 🧪 1️⃣ Unit Tests for Repository Layer
- Implemented test for `findByFullNameContainingIgnoreCase()`
- Used in-memory H2 database for isolation
- `@DataJpaTest` to load only the persistence layer

### 🧪 2️⃣ Unit Tests for Service Layer
- Used **Mockito** to mock repositories
- Verified:
    - Add user logic
    - Fetch all users
    - Fetch user by ID
    - Update user logic
    - Delete user handling
    - Search users

### 🧪 3️⃣ Integration Tests for Controller Layer (MockMvc)
- Tested all User endpoints:
    - ✅ Create User — `POST /api/v1/users`
    - ✅ Get All Users — `GET /api/v1/users`
    - ✅ Get User By ID — `GET /api/v1/users/{userId}`
    - ✅ Update User — `PUT /api/v1/users/{userId}`
    - ✅ Delete User — `DELETE /api/v1/users/{userId}`
    - ✅ Search Users — `GET /api/v1/users/search?keyword=...`

- Verified:
    - Correct Response Status Codes
    - APIResponse JSON structure
    - Returned Data Accuracy

---

## ⚠ Note
Due to time constraints and to ensure a functional MVP delivery:

📌 **Unit testing was focused on the User module for this week**  

---

## 🧰 Tools & Technologies Used
| Tool | Purpose |
|------|---------|
| JUnit 5 | Unit Testing Framework |
| Mockito | Mocking dependencies |
| Spring MockMvc | Testing REST controllers |
| H2 Database | Isolated DB testing |
| APIResponse wrapper | Unified API output |

---

## 📌 Acceptance Criteria Checklist ✅

| Requirement | Status |
|------------|--------|
| Unit Testing Implemented | ✅ |
| Tests without hitting real database | ✅ |
| Uses MockMvc for API Calls | ✅ |
| Uses Mockito for mocking | ✅ |
| Test covers CRUD operations | ✅ |
| Clear documentation of outcomes | ✅ |

---

## 🚀 Final Result
All implemented tests **passed successfully ✅**  
Application behavior is now validated for core User features  
making the system more stable and production-ready.

---

## 🚀 🧪 How to RunTest Execution

---
1. Clone the repository
   ```bash
   git clone https://github.com/MuhammedBakor/FlexiSAF-Internship-MohammadBakurIbrahim.git

2. Navigate to week9

   ```bash
   cd week8/backend

3. Make sure PostgreSQL is running and update application.yaml with your DB credentials.

4. Run the app

   ```bash
   mvn test

---

## ✅ Deliverable & Summary of Changes This Week

- Added structured testing layers to ensure application reliability
- Improved service logic testability by mocking dependencies
- Ensured API endpoints are functioning with expected responses
- Validated persistence layer using in-memory DB

---


## 📚 🧠 🎓 Learning Outcome

During Week 9, I achieved the following:

✅ Learned how to write effective unit tests for different layers (Repository, Service, Controller)  
✅ Practiced using Mockito to mock dependencies and isolate business logic  
✅ Learned to use MockMvc for testing REST APIs without starting the server  
✅ Improved understanding of API response validation including status codes and JSON structure  
✅ Configured H2 database for clean and optimized repository testing  
✅ Enhanced my debugging and test-driven thinking for cleaner and maintainable code  
✅ Gained deeper knowledge of Spring testing best practices and how to ensure high software reliability  



