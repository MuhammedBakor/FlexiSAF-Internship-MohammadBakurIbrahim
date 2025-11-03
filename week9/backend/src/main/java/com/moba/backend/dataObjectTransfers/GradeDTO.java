package com.moba.backend.dataObjectTransfers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record GradeDTO(
        Integer id,
        Integer subject_id,
        String subject_title,
        Integer student_id,
        String student_name,
        float score,
        String grade_type,
        LocalDateTime date_recorded
){
}
