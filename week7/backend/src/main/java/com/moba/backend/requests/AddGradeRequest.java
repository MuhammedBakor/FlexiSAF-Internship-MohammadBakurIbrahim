package com.moba.backend.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddGradeRequest {

    @NotNull(message = "Score cannot be empty")
    private float score;

    @NotBlank(message = "Grade type cannot be empty")
    private String grade_type;

    @NotNull(message = "Student ID title cannot be empty")
    private Integer student_id;

    @NotBlank(message = "Subject title cannot be empty")
    private String subject_title;


    public AddGradeRequest(
            float score,
            String grade_type,
            Integer student_id,
            String subject_title) {
        this.score = score;
        this.grade_type = grade_type;
        this.student_id = student_id;
        this.subject_title = subject_title;
    }
}
