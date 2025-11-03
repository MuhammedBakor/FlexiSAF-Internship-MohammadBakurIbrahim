package com.moba.backend.requests;
import java.time.LocalDate;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UserRegistrationRequest {

    @NotBlank(message = "Full name is required")
    @Size(min = 3, max = 100, message = "Full name must be between 3 and 100 characters")
    public String full_name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String password;

    @NotBlank(message = "Gender is required")
    private String gender;

    @Past(message = "Date of birth must be in the past")
    private LocalDate date_of_birth;

    @NotBlank(message = "Role name is required")
    private String role_name;

    public UserRegistrationRequest() {}

    public UserRegistrationRequest(String full_name, String email, String password, String gender, LocalDate date_of_birth, String role_name) {
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.role_name = role_name;
    }

    public String role_name() {
        return role_name;
    }
}
