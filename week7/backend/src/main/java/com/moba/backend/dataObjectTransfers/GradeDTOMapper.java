package com.moba.backend.dataObjectTransfers;

import com.moba.backend.models.Grade;
import com.moba.backend.models.Role;
import com.moba.backend.models.Student;
import com.moba.backend.models.UserData;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GradeDTOMapper implements Function<Grade, GradeDTO> {

    @Override
    public GradeDTO apply(Grade grade) {


        return new GradeDTO(
               grade.getId(),
                grade.getSubject().getId(),
                grade.getSubject().getTitle(),
                grade.getStudent().getId(),
                grade.getStudent().getUser().getFull_name(),
                grade.getScore(),
                grade.getGrade_type(),
                grade.getDate_recorded()
        );
    }
}
