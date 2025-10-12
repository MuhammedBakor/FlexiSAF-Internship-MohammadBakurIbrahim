package com.moba.backend.requests;

import java.time.LocalDate;

public record UserRegistrationRequest(
        String full_name,
        String email,
        String password,
        String gender,
        LocalDate date_of_birth,
        String role_name
){
}
