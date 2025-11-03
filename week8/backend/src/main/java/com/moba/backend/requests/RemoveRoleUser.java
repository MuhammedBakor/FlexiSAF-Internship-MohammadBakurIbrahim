package com.moba.backend.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RemoveRoleUser{

    @NotNull(message = "Role id cannot be empty")
    private Integer roleId;

    @NotNull(message = "User id cannot be empty")
    private Integer userId;

}
