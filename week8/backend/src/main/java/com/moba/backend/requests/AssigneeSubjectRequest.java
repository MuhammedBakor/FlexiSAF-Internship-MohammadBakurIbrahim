package com.moba.backend.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssigneeSubjectRequest {

    @NotNull(message = "Teacher id cannot be null")
    private Integer teacherId;

    @NotNull(message = "Subject id cannot be null")
    private Integer subjectId;

}
