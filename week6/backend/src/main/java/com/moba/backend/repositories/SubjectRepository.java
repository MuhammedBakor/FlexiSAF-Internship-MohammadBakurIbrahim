package com.moba.backend.repositories;

import com.moba.backend.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
}
