package com.moba.backend.dataObjectTransfers;

import com.moba.backend.models.Role;
import com.moba.backend.models.Subject;
import com.moba.backend.models.Teacher;
import com.moba.backend.models.UserData;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TeacherDTOMapper implements Function<Teacher, TeacherDTO> {

    @Override
    public TeacherDTO apply(Teacher teacher) {
        return new TeacherDTO(
                teacher.getId(),
                teacher.getAssigned_date(),
                teacher.getUpdated_date(),
                teacher.getUser().getId(),
                teacher.getUser().getFull_name(),
                teacher.getSubjects()
                        .stream()
                        .map(Subject::getTitle)
                        .collect(Collectors.toSet())
        );
    }
}
