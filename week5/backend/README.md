# Week 5 - Database Migration with Flyway

## 🎯 Task
- Show Implementation to Database Migration using Flyway.

---
## Summary of Changes
This week, I added:
-  **Flyway dependency** in `pom.xml`.
- New folder for flyway migrations under src/main/resources`/db/migration`
- Configured Flyway in `application.yaml` to manage database migrations.
- Disabled Hibernate auto schema generation to (`ddl-auto=validate`).
- Created migration `V1__add_gpa_column_to_student_table` to demonstrate incremental schema changes.
- Verified that on application startup, Flyway automatically applied the SQL scripts in order.
---

## 📖 Description
This week’s task focused on implementing database migration using **Flyway**.  
Flyway is a tool that provides version control for database schemas. It ensures that schema changes are applied in a controlled, repeatable way using migration scripts.

## Migration Strategy
- All migration files are stored in:

`src/main/resources/db/migration/`

---

## 🚀 How to Run

---
1. Clone the repository
   ```bash
   git clone https://github.com/MuhammedBakor/FlexiSAF-Internship-MohammadBakurIbrahim.git

2. Navigate to week5

   ```bash
   cd week5/backend

3. Make sure PostgreSQL is running and update application.yaml with your DB credentials.

4. Run the app

   ```bash
   mvn spring-boot:run

5. Flyway will automatically check for pending migrations and apply them.
---
## What I Added New

#### In this week, I extended the project by adding the following:

1. Integrated Flyway into the Spring Boot project.

2. Replaced Hibernate auto schema generation with controlled migrations.

3. Created versioned SQL migration scripts (V1__add_gpa_column_to_student_table).

4. Verified automatic schema updates on application startup.

---

## 📚 Learning Outcome

- Learned how to implement database version control with Flyway.

- Understood migration best practices and naming conventions.

- Practiced incremental schema evolution (add column).

- Experienced how Flyway integrates with Spring Boot lifecycle.


