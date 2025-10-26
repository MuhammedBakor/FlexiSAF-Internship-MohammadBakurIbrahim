package com.moba.backend.repositories;

import com.moba.backend.models.Grade;
import com.moba.backend.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    List<Grade> findAllByStudent_Id(Integer studentId);

    long countBySubject(Subject subject);
}
