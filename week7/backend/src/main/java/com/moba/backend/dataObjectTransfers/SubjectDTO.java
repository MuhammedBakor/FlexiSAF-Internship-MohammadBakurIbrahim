package com.moba.backend.dataObjectTransfers;

import java.time.LocalDateTime;
import java.util.List;

public record SubjectDTO(
        Integer id,
        String title,
        List<String> teachers
){
}
