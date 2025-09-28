package com.moba.backend.repositories;

import com.moba.backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query("SELECT r FROM Role r WHERE r.role_name = ?1")
    Role findRoleByRole_name(String name);
}
