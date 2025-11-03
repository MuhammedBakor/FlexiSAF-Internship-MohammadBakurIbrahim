package com.moba.backend.repositories;

import com.moba.backend.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserData,Integer> {
    List<UserData> findByFullNameContainingIgnoreCase(String keyword);
}
