package com.moba.backend.dataObjectTransfers;

import com.moba.backend.models.Grade;
import com.moba.backend.models.Subject;
import com.moba.backend.models.Teacher;
import com.moba.backend.models.UserData;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SubjectDTOMapper implements Function<Subject, SubjectDTO> {

    @Override
    public SubjectDTO apply(Subject subject) {

        return new SubjectDTO(
                subject.getId(),
                subject.getTitle(),
                subject.getTeachers().stream()
                        .map(Teacher::getUser)
                        .map(UserData::getFull_name)
                        .toList()
        );
    }
}
