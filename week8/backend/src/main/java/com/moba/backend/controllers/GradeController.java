package com.moba.backend.controllers;

import com.moba.backend.requests.AddGradeRequest;
import com.moba.backend.requests.GradeUpdateRequest;
import com.moba.backend.responses.APIResponse;
import com.moba.backend.services.GradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    public GradeController() {
    }

    @PostMapping()
    public ResponseEntity<APIResponse> addGrade(
            @RequestBody AddGradeRequest gradeRequest) {
        gradeService.addGrade(gradeRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new APIResponse(
                        "Grade added successfully to the student id [ "+gradeRequest.getStudent_id()+" ]",
                        HttpStatus.CREATED.value()));
    }

    @GetMapping
    public ResponseEntity<APIResponse> getAllGrades() {
        return ResponseEntity.ok(
                new APIResponse("All Grades fetched", HttpStatus.OK.value(),
                gradeService.getAllGrades()));
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<APIResponse> getStudentGrades(@PathVariable Integer studentId) {
        return ResponseEntity.ok(
                new APIResponse("Student with id [ %s ] grades fetched".formatted(studentId), HttpStatus.OK.value(),
                        gradeService.getStudentGrades(studentId)));
    }

    @PutMapping()
    public ResponseEntity<APIResponse> updateGrade(
            @Valid @RequestBody GradeUpdateRequest updateRequest) {
        gradeService.updateGrade(updateRequest);
        return ResponseEntity.ok(
                new APIResponse("Grade with id [ %s ] updated".formatted(updateRequest.getGrade_id()), HttpStatus.OK.value()));
    }

    @DeleteMapping("/{grade_id}")
    public ResponseEntity<APIResponse> deleteGrade(@PathVariable Integer grade_id) {
        gradeService.deleteGrade(grade_id);
        return ResponseEntity.ok(new APIResponse("Grade deleted", HttpStatus.OK.value()));
    }
}
