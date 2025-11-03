package com.moba.backend.repositories;

import com.moba.backend.models.Role;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query("SELECT r FROM Role r WHERE r.role_name = ?1")
    Optional<Role> findRoleByName(String name);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END FROM Role r WHERE r.role_name = :roleName")
    boolean existsByRoleName(@Param("roleName") String roleName);
}
