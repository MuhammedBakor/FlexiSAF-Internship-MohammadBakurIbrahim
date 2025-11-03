package com.moba.backend.dataObjectTransfers;

import com.moba.backend.models.Grade;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GradeDTOMapper implements Function<Grade, GradeDTO> {

    @Override
    public GradeDTO apply(Grade grade) {


        return new GradeDTO(
               grade.getId(),
                grade.getSubject().getId(),
                grade.getSubject().getTitle(),
                grade.getStudent().getId(),
                grade.getStudent().getUser().getFullName(),
                grade.getScore(),
                grade.getGrade_type(),
                grade.getDate_recorded()
        );
    }
}
