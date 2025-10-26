package com.moba.backend.services;

import com.moba.backend.dataObjectTransfers.GradeDTO;
import com.moba.backend.dataObjectTransfers.GradeDTOMapper;
import com.moba.backend.dataObjectTransfers.StudentDTO;
import com.moba.backend.dataObjectTransfers.StudentDTOMapper;
import com.moba.backend.exceptions.DuplicateResourceException;
import com.moba.backend.exceptions.ResourceNotFoundException;
import com.moba.backend.models.Role;
import com.moba.backend.models.Student;
import com.moba.backend.models.UserData;
import com.moba.backend.repositories.GradeRepository;
import com.moba.backend.repositories.RoleRepository;
import com.moba.backend.repositories.StudentRepository;
import com.moba.backend.repositories.UserRepository;
import com.moba.backend.requests.StudentUpdateRequest;
import com.moba.backend.requests.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StudentDTOMapper studentDTOMapper;
    private final GradeRepository gradeRepository;
    private final GradeDTOMapper gradeDTOMapper;

    public StudentService(StudentRepository studentRepository, UserRepository userRepository, RoleRepository roleRepository, StudentDTOMapper studentDTOMapper, UserService userService, GradeRepository gradeRepository, GradeDTOMapper gradeDTOMapper) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.studentDTOMapper = studentDTOMapper;
        this.gradeRepository = gradeRepository;
        this.gradeDTOMapper = gradeDTOMapper;
    }

    public void assignStudentRole(Integer userId) {

        UserData userToAssign =  getUserById(userId);
        String roleName = "STUDENT";

        Role role = roleRepository.findRoleByName(roleName)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role [%s] not found".formatted(roleName)));

        if (userToAssign.getRoles().contains(role)) {
            throw new DuplicateResourceException(
                    "Role [%s] is already assigned to the user".formatted(roleName));
        }

        userToAssign.getRoles().add(role);
        userRepository.save(userToAssign);

        Student student = new Student();
        student.setUser(userToAssign);

        studentRepository.save(student);
    }

    public UserData getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(
                () -> new ResourceNotFoundException("User not found with id " + userId));
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentDTOMapper)
                .toList();
    }

    public StudentDTO getStudentById(Integer id) {
        return studentRepository.findById(id)
                .map(studentDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    public void updateStudent(Integer studentId, StudentUpdateRequest updatedStudent) {
        Student studentToUpdate = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));

        studentToUpdate.getUser().setFull_name(updatedStudent.getFull_name());
        studentToUpdate.getUser().setEmail(updatedStudent.getEmail());
        studentToUpdate.getUser().setPassword(updatedStudent.getPassword());
        studentToUpdate.getUser().setDate_of_birth(updatedStudent.getDate_of_birth());

        studentRepository.save(studentToUpdate);
    }


    public void deleteStudent(Integer id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cannot delete. Student not found with id " + id));

        UserData user = student.getUser();
        if (user != null) {
            String roleName = "STUDENT";

            Role studentRole = roleRepository.findRoleByName(roleName)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Role [%s] not found".formatted(roleName)));

            if (studentRole != null && user.getRoles().contains(studentRole)) {
                user.getRoles().remove(studentRole);
                studentRole.getUsers().remove(user);

                userRepository.save(user);
            }
        }

        studentRepository.deleteById(id);
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
}
