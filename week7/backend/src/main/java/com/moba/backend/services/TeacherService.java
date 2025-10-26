package com.moba.backend.services;

import com.moba.backend.dataObjectTransfers.StudentDTO;
import com.moba.backend.dataObjectTransfers.TeacherDTO;
import com.moba.backend.dataObjectTransfers.TeacherDTOMapper;
import com.moba.backend.exceptions.ResourceNotFoundException;
import com.moba.backend.models.Role;
import com.moba.backend.models.Student;
import com.moba.backend.models.Teacher;
import com.moba.backend.models.UserData;
import com.moba.backend.repositories.RoleRepository;
import com.moba.backend.repositories.TeacherRepository;
import com.moba.backend.repositories.UserRepository;
import com.moba.backend.requests.TeacherUpdateRequest;
import com.moba.backend.requests.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private TeacherDTOMapper teacherDTOMapper;

    @Autowired
    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setTeacherDTOMapper(TeacherDTOMapper teacherDTOMapper) {
        this.teacherDTOMapper = teacherDTOMapper;
    }

    public void assignTeacherRole(Integer userId) {

        UserData userToAssign =  getUserById(userId);
        String roleName = "TEACHER";

        Role role = roleRepository.findRoleByName(roleName)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role [%s] not found".formatted(roleName)));

        userToAssign.getRoles().add(role);
        userRepository.save(userToAssign);

        Teacher teacher = new Teacher();
        teacher.setUser(userToAssign);

        teacherRepository.save(teacher);
    }

    private UserData getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found with id " + userId));
    }

    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherDTOMapper)
                .toList();
    }


    public TeacherDTO getTeacherById(Integer teacher_id) {
        return teacherRepository.findById(teacher_id)
                .map(teacherDTOMapper)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Teacher with id [ %s ] not found".formatted(teacher_id)));
    }


    public void updateTeacher(Integer teacherId, TeacherUpdateRequest updatedRequest) {
        Teacher teacherToUpdate = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Teacher with id [ %s ] not found".formatted(teacherId)));

        teacherToUpdate.getUser().setFull_name(updatedRequest.getFull_name());
        teacherToUpdate.getUser().setEmail(updatedRequest.getEmail());
        teacherToUpdate.getUser().setPassword(updatedRequest.getPassword());
        teacherToUpdate.getUser().setDate_of_birth(updatedRequest.getDate_of_birth());

        teacherRepository.save(teacherToUpdate);
    }

    public void deleteTeacher(Integer teacherId) {

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException(
                     "Cannot delete. Teacher with id [ %s ] not found".formatted(teacherId)));

        UserData user = teacher.getUser();
        if (user != null) {
            String roleName = "TEACHER";

            Role teacherRole = roleRepository.findRoleByName(roleName)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Role [%s] not found".formatted(roleName)));

            if (teacherRole != null && user.getRoles().contains(teacherRole)) {
                user.getRoles().remove(teacherRole);
                teacherRole.getUsers().remove(user);

                userRepository.save(user);
            }
        }

        teacherRepository.deleteById(teacherId);
    }
}
