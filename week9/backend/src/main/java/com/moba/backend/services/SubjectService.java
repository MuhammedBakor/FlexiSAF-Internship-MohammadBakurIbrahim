package com.moba.backend.services;

import com.moba.backend.dataObjectTransfers.SubjectDTO;
import com.moba.backend.dataObjectTransfers.SubjectDTOMapper;
import com.moba.backend.dataObjectTransfers.TeacherDTOMapper;
import com.moba.backend.dataObjectTransfers.UserDTO;
import com.moba.backend.exceptions.ResourceNotFoundException;
import com.moba.backend.models.Role;
import com.moba.backend.models.Subject;
import com.moba.backend.models.Teacher;
import com.moba.backend.models.UserData;
import com.moba.backend.repositories.GradeRepository;
import com.moba.backend.repositories.SubjectRepository;
import com.moba.backend.repositories.TeacherRepository;
import com.moba.backend.requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;
    private SubjectDTOMapper  subjectDTOMapper;
    private TeacherRepository teacherRepository;
    private GradeRepository gradeRepository;

    @Autowired
    public void setSubjectRepository(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Autowired
    public void setSubjectDTOMapper(SubjectDTOMapper subjectDTOMapper) {
        this.subjectDTOMapper = subjectDTOMapper;
    }

    @Autowired
    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Autowired
    public void setGradeRepository(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public void addSubject(AddSubjectRequest request) {

        Subject newSubject = new Subject(
               request.getTitle()
        );

        subjectRepository.save(newSubject);

    }

    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(subjectDTOMapper)
                .toList();
    }

    public SubjectDTO getSubjectById(Integer subjectId) {
        return subjectRepository.findById(subjectId)
                .map(subjectDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Subject with id [ %s ] not found".formatted(subjectId)));
    }

    public void updateSubject(Integer subjectId, SubjectUpdateRequest request) {
        Subject subjectToUpdate = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Subject with id [ %s ] not found".formatted(subjectId)));

        subjectToUpdate.setTitle(request.getTitle());

        subjectRepository.save(subjectToUpdate);
    }

    public void deleteSubjectById(Integer subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                  "Cannot delete. Subject with id [ %s ] not found".formatted(subjectId)));

        long countGrades = gradeRepository.countBySubject(subject);
        if (countGrades > 0) {
            throw new IllegalStateException(
                    "Cannot delete subject because grades are linked to it. Remove all the related grades first");
        }

        long countTeachers = teacherRepository.countBySubjectsContaining(subject);
        if (countTeachers > 0) {
            throw new IllegalStateException(
                    "Cannot delete subject because teachers are linked to it. Unassign or remove the teachers first.");
        }

        subjectRepository.deleteById(subjectId);
    }

    public void assigneeSubject(AssigneeSubjectRequest request) {

        Subject subjectToAdd = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Subject with id [ %s ] not found".formatted(request.getSubjectId())));

        Teacher teacherToAssignee = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Teacher with id [ %s ] not found".formatted(request.getTeacherId())));

        teacherToAssignee.getSubjects().add(subjectToAdd);
        teacherRepository.save(teacherToAssignee);

    }

}
