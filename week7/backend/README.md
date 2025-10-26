# Week 7 – Implementing REST Resource Endpoints

## 🎯 Task
- Create a spring boot application with at least 4 endpoints, handle exception handling, 
validate inputs and create a service, repository and controller layer.

## 📖 Description
This week’s focus was on building RESTful endpoints using **Spring Boot**.  
The goal was to expose data through APIs, handle exceptions gracefully, validate user input, and follow best practices for REST API design.

The implementation builds upon the existing project from **Week 6**, enhancing it with REST Controllers and improved API structure.

---
## Summary of Changes
- Added **REST Controllers** for:
  - `UserController`
  - `RoleController`
  - `StudentController`
  - `TeacherController`
  - `GradeController`
  - `SubjectController`
- Implemented **4 CRUD endpoints** for each resource:
  - `GET /api/v1/{entity}`
  - `GET /api/v1/{entity}/{id}`
  - `POST /api/v1/{entity}`
  - `PUT /api/v1/{entity}/{id}`
  - `DELETE /api/v1/{entity}/{id}`
- Added **Input Validation** using:
  - `@Valid`, `@NotNull`, `@Email`, `@Size`
- Added **Global Exception Handling** via `GlobalExceptionHandler`
- Introduced **APIResponse** wrapper class to unify all responses.
- Enhanced **Service Layer** to support CRUD operations.

---

## Example Implementations
---
### Sample Controller (StudentController)
    
    @RestController
    @RequestMapping("/api/v1/users")
    public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create User
    @PostMapping
    public ResponseEntity<APIResponse> registerUser(
            @Valid @RequestBody UserRegistrationRequest request) {
        userService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new APIResponse("User created successfully",
                        HttpStatus.CREATED.value()));
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get one user
    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUser(@PathVariable Integer userId) {
        UserDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(new APIResponse("User retrieved successfully", HttpStatus.OK.value(), user));
    }

    // Update user
    @PutMapping("/{userId}")
    public ResponseEntity<APIResponse> updateUser(
            @PathVariable Integer userId,
            @Valid @RequestBody UserUpdateRequest request) {
        userService.updateUser(userId, request);
        return ResponseEntity.ok(new APIResponse("User updated successfully", HttpStatus.OK.value()));
    }

    // Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok(new APIResponse("User deleted successfully", HttpStatus.OK.value()));
    }
    }

---

### Global Exception Handler

    @ControllerAdvice
    public class GlobalExceptionHandler {
    
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<APIResponse> handleNotFound(ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
        }
    
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<APIResponse> handleValidation(MethodArgumentNotValidException ex) {
            String message = ex.getBindingResult().getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new APIResponse(message, HttpStatus.BAD_REQUEST.value()));
        }
    
        @ExceptionHandler(Exception.class)
        public ResponseEntity<APIResponse> handleGeneral(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }


___

### API Response Wrapper
    @Getter
    public class APIResponse {
    private String message;
    private int status;
    private Object data;
    private LocalDateTime timestamp = LocalDateTime.now();
    
        public APIResponse(String message, int status) {
            this.message = message;
            this.status = status;
        }
    
        public APIResponse(String message, int status, Object data) {
            this.message = message;
            this.status = status;
            this.data = data;
        }
    }

---

## 🚀 How to Run

---
1. Clone the repository
   ```bash
   git clone https://github.com/MuhammedBakor/FlexiSAF-Internship-MohammadBakurIbrahim.git

2. Navigate to week7

   ```bash
   cd week7/backend

3. Make sure PostgreSQL is running and update application.yaml with your DB credentials.

4. Run the app

   ```bash
   mvn spring-boot:run

5. First, add any role, so you can register a new user with the name of the role you inserted.

6. Test endpoints via Postman:

---

### (Role)
To create a new role with `AddRoleRequest` body
<br> POST http://localhost:8080/api/v1/roles

To get all roles
<br> GET http://localhost:8080/api/v1/roles

To get a role with id
<br> GET http://localhost:8080/api/v1/roles/{roleId}

To update a role with id and `RoleUpdateRequest` body
<br> PUT http://localhost:8080/api/v1/roles/{roleId}

To delete a role with id (you cannot delete until the related users removed)
<br> DELETE http://localhost:8080/api/v1/roles/{rolesId}

To remove a user from a role with `RemoveRoleUser` body
<br> DELETE http://localhost:8080/api/v1/roles

---
### (User)

To add a new user with `UserRegistrationRequest` body
<br> POST http://localhost:8080/api/v1/users

To get all users
<br> GET http://localhost:8080/api/v1/users

To get a user with userId
<br> GET http://localhost:8080/api/v1/users/{userId}

To update a user with userId and `UserUpdateRequest` body
<br> PUT http://localhost:8080/api/v1/users/{userId}

To delete a user with userId
<br> DELETE http://localhost:8080/api/v1/users/{userId}

---

### (Student)

First add a "STUDENT" role with `AddRoleRequest` body
<br> POST http://localhost:8080/api/v1/roles


To assign a student role the user with userId
<br> POST http://localhost:8080/api/v1/students/{userId}

To get all students
<br> GET http://localhost:8080/api/v1/students

To get a student with studentId
<br> GET http://localhost:8080/api/v1/students/{studentId}

To update a student with student id and `StudentUpdateRequest` body
<br> PUT http://localhost:8080/api/v1/students/{studentId}

To delete student with the id
<br> DELETE http://localhost:8080/api/v1/students/{studentId}

To get student grades with a student studentId
<br> GET http://localhost:8080/api/v1/students/grades/{studentId}
---

### (Teacher)

First add a "TEACHER" role with `AddRoleRequest` body
<br> POST http://localhost:8080/api/v1/roles


To assign a teacher role with user userId
<br> POST http://localhost:8080/api/v1/teachers/{userId}

To get all teachers
<br> GET http://localhost:8080/api/v1/teachers

To get teacher with teacher id
<br> GET http://localhost:8080/api/v1/teachers/{teacherId}

To update a teacher with teacher id and `TeacherUpdateRequest` body
<br> PUT http://localhost:8080/api/v1/teachers/{teacherId}

To delete teacher with the teacher id
<br> DELETE http://localhost:8080/api/v1/teachers/{teacherId}

---

### (Subject)

To add new subject with `AddSubjectRequest` body
<br> POST http://localhost:8080/api/v1/subjects

To get all subjects
<br> GET http://localhost:8080/api/v1/subjects

To get subject with subject id
<br> GET http://localhost:8080/api/v1/teachers/{subjectId}

To update a subject with subjects subjectId and `SubjectUpdateRequest` body
<br> PUT http://localhost:8080/api/v1/subjects/{subjectId}

To delete subject with the subject id (you cannot delete until the related grades deleted)
<br> DELETE http://localhost:8080/api/v1/subjects/{subjectId}

---

### (Grade)

To add Grade first add a subject with `AddSubjectRequest` body
<br> POST http://localhost:8080/api/v1/subjects

To add a grade to a student with `AddGradeRequest` body
<br> POST http://localhost:8080/api/v1/grades

To get all students' grades
<br> GET http://localhost:8080/api/v1/grades

To get student grades
<br> GET http://localhost:8080/api/v1/grades/students/{studentId}

To update a grade with `GradeUpdateRequest` body
<br> PUT http://localhost:8080/api/v1/grades

To delete a grade
<br> DELETE http://localhost:8080/api/v1/grades/{grade_id}

---
## Deliverable

This week’s deliverable includes:
- RESTful API endpoints for 5 entities.
- Input validation using annotations.
- Exception handling via @ControllerAdvice.
- Service, Repository, and Controller layers.
- Consistent JSON API response format.
---


## 📚 Learning Outcome
- **REST API Design:**
  - Use clear and consistent endpoint naming conventions (e.g., `/api/users`, `/api/students`).
  - Follow HTTP method semantics (`GET`, `POST`, `PUT`, `DELETE`).
  - Return proper status codes (`200 OK`, `201 Created`, `404 Not Found`, `400 Bad Request`, etc.).
- **Input Validation:**
  - Implemented with `@Valid` and annotations like `@NotNull`, `@Email`, `@Size`.
- **Exception Handling:**
  - Centralized using `@ControllerAdvice` and custom exception classes.
  - Standardized error responses (message, status, timestamp).
- **Dependency Injection:**
  - Continued to apply Service and Repository patterns for clean separation of logic.
- **Best Practices from DZone Article:**
  - Keep endpoints resource-based and intuitive.
  - Never expose database models directly.
  - Return JSON responses with consistent structure.
  - Use pagination and filtering for large datasets (demonstrated conceptually).



