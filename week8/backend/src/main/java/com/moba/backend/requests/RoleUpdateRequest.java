package com.moba.backend.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleUpdateRequest {

    @NotBlank(message = "Role name cannot be empty")
    private String role_name;

}
