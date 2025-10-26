package com.moba.backend.controllers;

import com.moba.backend.models.Student;
import com.moba.backend.requests.StudentUpdateRequest;
import com.moba.backend.requests.UserUpdateRequest;
import com.moba.backend.responses.APIResponse;
import com.moba.backend.services.GradeService;
import com.moba.backend.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;
    private final GradeService gradeService;

    public StudentController(StudentService studentService, GradeService gradeService) {
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<APIResponse> assignStudent(@PathVariable Integer userId) {
        studentService.assignStudentRole(userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new APIResponse(
                        "Student Role added successfully to the user id [ "+userId+" ]",
                        HttpStatus.CREATED.value()));
    }

    @GetMapping
    public ResponseEntity<APIResponse> getAllStudents() {
        return ResponseEntity.ok(
                new APIResponse("All students fetched", HttpStatus.OK.value(),
                studentService.getAllStudents()));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<APIResponse> getStudent(@PathVariable Integer studentId) {
        return ResponseEntity.ok(new APIResponse("Student retrieved", HttpStatus.OK.value(),
                studentService.getStudentById(studentId)));
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<APIResponse> updateStudent(@PathVariable Integer studentId, @Valid @RequestBody StudentUpdateRequest updateRequest) {
        studentService.updateStudent(studentId, updateRequest);
        return ResponseEntity.ok(new APIResponse("Student updated", HttpStatus.OK.value()));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<APIResponse> deleteStudent(@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok(new APIResponse("Student deleted", HttpStatus.OK.value()));
    }


    @GetMapping("/grades/{studentId}")
    public ResponseEntity<APIResponse> getStudentGrades(@PathVariable Integer studentId) {
        return ResponseEntity.ok(
                new APIResponse("Student with id [ %s ] grades fetched".formatted(studentId), HttpStatus.OK.value(),
                        studentService.getStudentGrades(studentId)));
    }
}
