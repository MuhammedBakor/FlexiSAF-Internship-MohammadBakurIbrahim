package com.moba.flexisafinternshipbackend.repositories;

import com.moba.flexisafinternshipbackend.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserData, Integer> {

}
