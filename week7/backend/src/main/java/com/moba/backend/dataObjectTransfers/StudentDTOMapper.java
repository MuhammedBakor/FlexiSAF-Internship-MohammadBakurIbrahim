package com.moba.backend.dataObjectTransfers;

import com.moba.backend.models.Role;
import com.moba.backend.models.Student;
import com.moba.backend.models.UserData;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StudentDTOMapper implements Function<Student, StudentDTO> {
    @Override
    public StudentDTO apply(Student student) {

        UserData userStudent = student.getUser();

        return new StudentDTO(
                student.getId(),
                userStudent.getFull_name(),
                userStudent.getEmail(),
                userStudent.getGender(),
                userStudent.getDate_of_birth(),
                userStudent.getRoles()
                        .stream().map(Role::getRole_name)
                        .collect(Collectors.toSet())
        );
    }
}
