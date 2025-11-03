package com.moba.backend.services;

import com.moba.backend.dataObjectTransfers.GradeDTO;
import com.moba.backend.dataObjectTransfers.GradeDTOMapper;
import com.moba.backend.exceptions.ResourceNotFoundException;
import com.moba.backend.models.Grade;
import com.moba.backend.models.Student;
import com.moba.backend.models.Subject;
import com.moba.backend.repositories.GradeRepository;
import com.moba.backend.repositories.StudentRepository;
import com.moba.backend.repositories.SubjectRepository;
import com.moba.backend.requests.AddGradeRequest;
import com.moba.backend.requests.GradeUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    GradeDTOMapper gradeDTOMapper;

    GradeService(){}

    public void addGrade(AddGradeRequest  request) {

        Student student = studentRepository.findById(request.getStudent_id())
          .orElseThrow(() ->
            new ResourceNotFoundException("Student with id [%s] not found".formatted(request.getStudent_id())));

       Subject subject =  subjectRepository.findByTitle(request.getSubject_title())
          .orElseThrow(() ->
            new ResourceNotFoundException("Subject with title [%s] not found".formatted(request.getSubject_title())));

        Grade grade = new Grade(
                request.getScore(),
                request.getGrade_type()
        );

        grade.setStudent(student);
        grade.setSubject(subject);

        gradeRepository.save(grade);
    }

    public List<GradeDTO> getAllGrades() {
        return gradeRepository.findAll()
                .stream()
                .map(gradeDTOMapper)
                .toList();
    }

    public List<GradeDTO> getStudentGrades(Integer studentId) {
        List<GradeDTO> studentGrades = gradeRepository.findAllByStudent_Id(studentId)
                .stream()
                .map(gradeDTOMapper)
                .toList();

        if (studentGrades.isEmpty()){
            throw new ResourceNotFoundException(
                    "There are not grades related to student with id [ %s ]".formatted(studentId));
        }

        return studentGrades;
    }

    public void updateGrade(GradeUpdateRequest updateRequest) {
        Grade gradeToUpdate = gradeRepository.findById(updateRequest.getGrade_id())
                .orElseThrow(() -> new ResourceNotFoundException("Grade with id [%s] not found".formatted(updateRequest.getGrade_id())));

        Subject subject =  subjectRepository.findByTitle(updateRequest.getSubject_title())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Subject with title [%s] not found".formatted(updateRequest.getSubject_title())));


        gradeToUpdate.setScore(updateRequest.getScore());
        gradeToUpdate.setGrade_type(updateRequest.getGrade_type());
        gradeToUpdate.setSubject(subject);

        gradeRepository.save(gradeToUpdate);

    }

    public void deleteGrade(Integer grade_id) {
        if (!gradeRepository.existsById(grade_id)) {
            throw new ResourceNotFoundException("Cannot delete. Grade with id [ %s ] not found".formatted(grade_id));
        }
        gradeRepository.deleteById(grade_id);
    }
}
