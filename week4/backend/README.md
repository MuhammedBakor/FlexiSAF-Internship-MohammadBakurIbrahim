# Week 4 - Persistence Frameworks (JPA / Hibernate)


## 🎯 Task
- Use important Hibernate annotation to design a database table(entity) at least 10 fields.

---
## Summary of Changes
This week, I added:
- A new entity named **`UserProfile`** (an academic/User table) with **more than 10 fields**, demonstrating common JPA/Hibernate annotations 
(`@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`, `@Enumerated`, `@Embedded`,
`@Lob`, `@Version`, `@CreationTimestamp`, `@UpdateTimestamp`, etc.). 
These annotations show common mapping patterns and behaviour used by Hibernate.
- A **JPA repository** for `UserProfile` (extends `JpaRepository`) to handle persistence.
- A **service class** with methods for CRUD operations and an example JPQL query.
- A small **CommandLineRunner** (or test data snippet) to persist sample entries to the DB for verification.

---

## 📖 Description
This week, I designed a new entity **UserProfile** with more than 10 fields, applying important Hibernate/JPA annotations.  
The focus is on ORM concepts, entity persistence, and querying with JPQL.

## UserProfile Entity
**Key fields & why**
- **id** → primary key with auto generation.
- **full_name** → string, required.
- **email** → string, unique, required.
- **phone_number** → string.
- **gender** → string (instead of enum).
- **role** → string (ADMIN, STUDENT, TEACHER, etc.).
- **date_of_birth** → LocalDate.
- **address** → embedded object.
- **created_at** → `@CreationTimestamp`.
- **updated_at** → `@UpdateTimestamp`.
- **enabled** → boolean.
- **profile_picture** → `@Lob` for binary data.
- **version** → `@Version` for optimistic locking.

This design covers over 10 fields and demonstrates practical ORM annotations.

---

### Note on Entities (UserData vs UserProfile)
In previous weeks (Week 2 & Week 3), I had already implemented an entity called **`UserData`** with basic fields such as `full_name`, `email`, `password`, `gender`, `date_of_birth`, `enabled`, and a Many-to-Many relationship with `Role`.

For this week (Week 4), the requirement was to design an entity with at least **10 fields** and demonstrate important Hibernate/JPA annotations.  
To meet this requirement, I introduced a **new entity called `UserProfile`**, which extends beyond `UserData` by including additional fields such as `address` (embedded object), `timestamps` (`@CreationTimestamp`, `@UpdateTimestamp`), `@Lob` fields for large data, and `@Version` for optimistic locking.
<br> And **`UserProfile`** will be created automatically when we create `UserData`, and linked to `UserData`.

This way:
- **`UserData`** remains as the base entity from earlier weeks.
- **`UserProfile`** serves as the new entity showcasing advanced ORM mappings and Hibernate features required in Week 4.

---

## 🚀 How to Run

---
1. Clone the repository
   ```bash
   git clone https://github.com/MuhammedBakor/FlexiSAF-Internship-MohammadBakurIbrahim.git

2. Navigate to week4

   ```bash
   cd week4/backend

3. Make sure PostgreSQL is running and update application.yaml with your DB credentials.


4. Run the app

   ```bash
   mvn spring-boot:run

5. Test the endpoints using Postman or browser:

 - http://localhost:8080/api/v1/users
---

## What I Added New

#### In this week, I extended the project by adding the following components:


1. `UserProfile` entity with 10+ fields and annotations (`@Entity`, `@Table`, `@Embedded`, `@Lob`, `@Version`, timestamps, etc.).

2. `Address` embeddable object.

3. `UserProfileRepository` (JpaRepository) with JPQL query and derived query method.

4. `UserProfileService` (CRUD and role-based query methods).

---

## 📚 Learning Outcome
- Practiced mapping Java classes to tables using JPA/Hibernate annotations.

- Implemented an entity with complex fields (embedded object, LOBs, enums, versioning).

- Wrote JPQL/HQL-style queries and learned when to use derived queries vs. @Query.

- Learnt how Hibernate handles identifier generation, timestamps, and optimistic locking.
 


