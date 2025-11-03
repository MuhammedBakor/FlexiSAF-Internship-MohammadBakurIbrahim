package com.moba.backend.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddRoleRequest {

    @NotBlank(message = "Role name cannot be empty")
    private String role_name;


    public AddRoleRequest( String role_name) {
        this.role_name = role_name;
    }
}
