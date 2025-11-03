package com.moba.backend.dataObjectTransfers;

import java.time.LocalDate;
import java.util.Set;

public record StudentDTO (
        Integer id,
        String student_name,
        String email,
        String gender,
        LocalDate date_of_birth,
        Set<String> roles
){
}
