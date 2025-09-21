package com.moba.backend.repositories;

import com.moba.backend.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, Integer> {

}
