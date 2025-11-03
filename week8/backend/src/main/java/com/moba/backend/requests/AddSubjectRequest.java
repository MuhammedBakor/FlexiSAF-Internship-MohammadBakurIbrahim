package com.moba.backend.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddSubjectRequest {

    @NotBlank(message = "Subject title cannot be empty")
    private String title;

}
