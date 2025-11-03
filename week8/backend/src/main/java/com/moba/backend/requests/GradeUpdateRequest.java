package com.moba.backend.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GradeUpdateRequest {

    @NotNull(message = "Grade ID title cannot be empty")
    private Integer grade_id;

    @NotNull(message = "Score cannot be empty")
    private float score;

    @NotBlank(message = "Grade type cannot be empty")
    private String grade_type;

    @NotBlank(message = "Subject title cannot be empty")
    private String subject_title;


    public GradeUpdateRequest(
            float score,
            String grade_type,
            Integer grade_id,
            String subject_title) {
        this.score = score;
        this.grade_type = grade_type;
        this.grade_id = grade_id;
        this.subject_title = subject_title;
    }
}
