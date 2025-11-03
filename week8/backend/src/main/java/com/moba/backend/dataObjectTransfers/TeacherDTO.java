package com.moba.backend.dataObjectTransfers;

import com.moba.backend.models.Subject;

import java.time.LocalDateTime;
import java.util.Set;

public record TeacherDTO(
        Integer id,
        LocalDateTime assigned_date,
        LocalDateTime updated_date,
        Integer user_id,
        String user_name,
        Set<String> subjects
){
}
