package com.moba.backend.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TeacherUpdateRequest {

    @NotBlank(message = "Full name cannot be empty")
    @Size(min = 3, max = 100, message = "Full name must be between 3 and 100 characters")
    private String full_name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String password;

    @Past(message = "Date of birth must be in the past")
    private LocalDate date_of_birth;

    public TeacherUpdateRequest() {}

    public TeacherUpdateRequest(String full_name, String email, String password, LocalDate date_of_birth) {
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.date_of_birth = date_of_birth;
    }
}
