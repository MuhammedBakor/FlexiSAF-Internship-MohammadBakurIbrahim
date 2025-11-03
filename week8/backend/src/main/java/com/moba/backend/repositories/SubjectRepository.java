package com.moba.backend.repositories;

import com.moba.backend.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {

   // @Query("SELECT s FROM Subject s WHERE s.title = ?1")
    Optional<Subject> findByTitle(String title);
}
