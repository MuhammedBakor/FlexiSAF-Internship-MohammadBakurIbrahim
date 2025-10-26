package com.moba.backend.controllers;

import com.moba.backend.requests.TeacherUpdateRequest;
import com.moba.backend.responses.APIResponse;
import com.moba.backend.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<APIResponse> assignTeacher(@PathVariable Integer userId) {
        teacherService.assignTeacherRole(userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new APIResponse(
                        "Teacher Role added successfully to the user id [ %s ]".formatted(userId),
                        HttpStatus.CREATED.value()));
    }

    @GetMapping
    public ResponseEntity<APIResponse> getAllTeachers() {
        return ResponseEntity.ok(
                new APIResponse("All teachers fetched", HttpStatus.OK.value(),
                teacherService.getAllTeachers()));
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<APIResponse> getTeacher(@PathVariable Integer teacherId) {
        return ResponseEntity.ok(new APIResponse("Teacher with id [ %s ] retrieved".formatted(teacherId), HttpStatus.OK.value(),
                teacherService.getTeacherById(teacherId)));
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<APIResponse> updateTeacher(
            @PathVariable Integer teacherId,
            @Valid @RequestBody TeacherUpdateRequest updateRequest) {
        teacherService.updateTeacher(teacherId, updateRequest);
        return ResponseEntity.ok(new APIResponse("Teacher with id [ %s ] updated".formatted(teacherId), HttpStatus.OK.value()));
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<APIResponse> deleteTeacher(@PathVariable Integer teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.ok(new APIResponse(
                "Teacher deleted", HttpStatus.OK.value()));
    }


//    @GetMapping("/subjects/{teacherId}")
//    public ResponseEntity<APIResponse> getTeacherSubjects(@PathVariable Integer teacherId) {
//        return ResponseEntity.ok(
//                new APIResponse("Teacher with id [ %s ] subjects fetched".formatted(teacherId), HttpStatus.OK.value(),
//                        subjectService.getTeacherSubjects(teacherId)));
//    }
}
