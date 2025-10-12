package com.moba.backend.repositories;

import com.moba.backend.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    // Example JPQL: find all users with given role
    @Query("SELECT u FROM UserProfile u JOIN u.roles r WHERE r = :role")
    List<UserProfile> findByRole(String role);

    // Derived query: find by email
    UserProfile findByEmail(String email);
}
