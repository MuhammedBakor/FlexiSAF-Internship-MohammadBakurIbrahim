package com.moba.backend.repositories;

import com.moba.backend.models.Subject;
import com.moba.backend.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    long countBySubjectsContaining(Subject subject);
}
