package com.moba.backend.requests;

import java.time.LocalDate;
import java.util.Set;

public record UserDTO(
        Integer id,
        String full_name,
        String email,
        String gender,
        LocalDate date_of_birth,
        Set<String> roles
){
}
