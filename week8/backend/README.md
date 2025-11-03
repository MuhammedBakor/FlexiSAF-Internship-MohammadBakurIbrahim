# Week 8 – RESTful API Best Practices

## 🎯 Task
- Apply best practices learnt here to previous projects, examples to be shown.


## 📖 Description
During this week, I reviewed and refined my project to ensure full compliance with RESTful API best practices.  
Fortunately, most of these improvements had already been implemented in **Week 7**, including proper endpoint design, HTTP response codes, validation, and global exception handling.

This week focused on validating and enhancing those standards for even cleaner, production-ready code.

Reference: [DZone - REST API Best Practices](https://dzone.com/articles/rest-api-best-practices-with-design-examples-from)


---

## ✅ Previously Implemented Best Practices
The following best practices were **already integrated in Week 7**:
- RESTful resource naming conventions for all endpoints.
  - `/api/v1/users`
  - `/api/v1/students`
  - `/api/v1/subjects`
  - `/api/v1/teachers`
- Each URL clearly represents a **resource** instead of an action.
- Consistent use of HTTP methods (`GET`, `POST`, `PUT`, `DELETE`).
- Standardized response codes (`200`, `201`, `204`, `400`, `404`, `500`).
- Input validation using Jakarta annotations (`@NotBlank`, `@Email`, `@Past`).
- Centralized error handling via `GlobalExceptionHandler`.
- Unified API response model (`APIResponse`).
- Clean package structure for maintainability.
- Proper dependency injection across services and controllers.


---

## 🧩 Enhancements Added in Week 8
Although most best practices were already implemented earlier, I added a few **minor refinements** to further improve the project quality:

1. **Search and Query Parameters**
    - Added a new search feature that allows filtering users by name.
    - Endpoint example:  
      `GET /api/v1/users/search?keyword=Mo`
    - This helps quickly find users based on partial or full name matches and makes the API more user-friendly and practical.


2. **Enhanced APIResponse Structure**
  - Added automatic timestamps to all API responses for better traceability.

3. **Logging with Lombok (`@Slf4j`)**
  - Implemented structured logging in controllers for easier debugging and monitoring.

4. **Code Review and Cleanup**
  - Removed redundant comments and ensured consistent naming conventions across all packages.

---

## 🧱 Project Structure

    src.main.java.com.moba.backend
    ├── controllers
    ├── dataObjectTransfers
    ├── exceptions
    ├── models
    ├── repositories
    ├── requests
    ├── responses
    ├── services
    └── Main.java

---

## 🚀 How to Run

---
1. Clone the repository
   ```bash
   git clone https://github.com/MuhammedBakor/FlexiSAF-Internship-MohammadBakurIbrahim.git

2. Navigate to week8

   ```bash
   cd week8/backend

3. Make sure PostgreSQL is running and update application.yaml with your DB credentials.

4. Run the app

   ```bash
   mvn spring-boot:run

---

## Deliverable

This week’s deliverable includes:
- RESTful API endpoints for 5 entities.
- Input validation using annotations.
- Exception handling via @ControllerAdvice.
- Service, Repository, and Controller layers.
- Consistent JSON API response format.
---


## 📚 🧠 Learning Outcome
By the end of Week 8, I confirmed that my project follows:

- RESTful design standards.
- Proper HTTP method usage and status codes.
- Consistent response and error structure.
- Clean architecture for scalability.

This week reinforced the importance of maintaining API consistency, clarity, and documentation — ensuring that the backend is both professional and easy to maintain.



