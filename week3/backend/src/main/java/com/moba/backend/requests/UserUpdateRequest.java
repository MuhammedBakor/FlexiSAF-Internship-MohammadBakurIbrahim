package com.moba.backend.requests;

import java.time.LocalDate;

public record UserUpdateRequest(
        String full_name,
        String email,
        String password,
        LocalDate date_of_birth
){
}
