# Week 6 – Implementing Service Classes with Dependency Injection

## 🎯 Task
- Practice dependency injection using constructors and setters and fields

## 📖 Description
In this week, the focus was on **implementing service classes** in the Spring Boot project 
and practicing **Dependency Injection (DI)** using 
different approaches (Constructor, Setter, and Field injection) to properly separate 
the business logic from the Controller and Repository layers while following best practices for maintainability, testability, and scalability..

---
## Summary of Changes
- **Entities and Repositories kept:**
   - `Role`, `UserData`, `Student`, `Teacher`, `Subject`, `Grade`
- **Removed unused entities** due to time constraints.
- **Service classes created and DI applied as follows:**
   - **UserService** – Constructor Injection
   - **StudentService** – Constructor Injection
   - **TeacherService** – Setter Injection
   - **SubjectService** – Setter Injection
   - **GradeService** – Field Injection

[//]: # (- **Controllers updated** to use their respective services instead of directly calling repositories.)

---

## Example Implementations
---
### Constructor Injection (UserService & StudentService)
      @Service
      public class UserService {
          private final UserDataRepository userDataRepository;
      
          public UserService(UserDataRepository userDataRepository) {
              this.userDataRepository = userDataRepository;
          }
      
          public List<UserData> getAllUsers() {
              return userDataRepository.findAll();
          }
      }
---

### Setter Injection (TeacherService & SubjectService)

      @Service
      public class TeacherService {
      private TeacherRepository teacherRepository;
      
          @Autowired
          public void setTeacherRepository(TeacherRepository teacherRepository) {
              this.teacherRepository = teacherRepository;
          }
      
          public List<Teacher> getAllTeachers() {
              return teacherRepository.findAll();
          }
      }

---

### Field Injection (GradeService)

      @Service
      public class GradeService {
      @Autowired
      private GradeRepository gradeRepository;
      
          public List<Grade> getAllGrades() {
              return gradeRepository.findAll();
          }
      }

---

## 🚀 How to Run

---
1. Clone the repository
   ```bash
   git clone https://github.com/MuhammedBakor/FlexiSAF-Internship-MohammadBakurIbrahim.git

2. Navigate to week6

   ```bash
   cd week6/backend

3. Make sure PostgreSQL is running and update application.yaml with your DB credentials.

4. Run the app

   ```bash
   mvn spring-boot:run

---

## 📚 Learning Outcome
- **Dependency Injection (DI):**
   - A design pattern that allows Spring to manage dependencies automatically.
   - Promotes loose coupling between classes and makes testing easier.
- **Types of Dependency Injection practiced:**
   - **Constructor Injection:** Preferred method; ensures immutability and is easier to test.
   - **Setter Injection:** Useful for optional dependencies or when objects may change during runtime.
   - **Field Injection:** Quick and concise, but less recommended for complex applications (used here for demonstration).
- **Best Practices:**
   - Keep business logic inside `@Service` classes.
   - Controllers should only delegate requests to the Service layer.
   - Repositories should only handle database interactions.



