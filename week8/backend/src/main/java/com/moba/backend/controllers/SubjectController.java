package com.moba.backend.controllers;

import com.moba.backend.dataObjectTransfers.SubjectDTO;
import com.moba.backend.dataObjectTransfers.UserDTO;
import com.moba.backend.requests.*;
import com.moba.backend.responses.APIResponse;
import com.moba.backend.services.SubjectService;
import com.moba.backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {

        this.subjectService = subjectService;
    }

    // Add Subject
    @PostMapping
    public ResponseEntity<APIResponse> addSubject(
            @Valid @RequestBody AddSubjectRequest request) {
        subjectService.addSubject(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new APIResponse("Subject [ %s ] added successfully".formatted(request.getTitle()),
                        HttpStatus.CREATED.value()));
    }

    // Get all subject
    @GetMapping
    public ResponseEntity<APIResponse> getAllSubject() {
        return ResponseEntity.ok(
                new APIResponse("All Grades fetched", HttpStatus.OK.value(),
                        subjectService.getAllSubjects()));
    }

    // Get subject with id
    @GetMapping("/{subjectId}")
    public ResponseEntity<APIResponse> getSubject(@PathVariable Integer subjectId) {
        SubjectDTO subject = subjectService.getSubjectById(subjectId);
        return ResponseEntity.ok(
                new APIResponse(
                        "Subject id [ %s ] retrieved successfully".formatted(subjectId),
                        HttpStatus.OK.value(),
                        subject));
    }

    // Update Subject
    @PutMapping("/{subjectId}")
    public ResponseEntity<APIResponse> updateSubject(
            @PathVariable Integer subjectId,
            @Valid @RequestBody SubjectUpdateRequest request) {
        subjectService.updateSubject(subjectId, request);
        return ResponseEntity.ok(new APIResponse(
                "Subject updated successfully", HttpStatus.OK.value()));
    }

    // Delete subject
    @DeleteMapping("/{subjectId}")
    public ResponseEntity<APIResponse> deleteSubject(@PathVariable Integer subjectId) {
        subjectService.deleteSubjectById(subjectId);
        return ResponseEntity.ok(new APIResponse(
                "Subject with id [ %s ] deleted successfully".formatted(subjectId)
                , HttpStatus.OK.value()));
    }

    // Assignee Subject to teacher
    @PostMapping("/assignee")
    public ResponseEntity<APIResponse> AssigneeSubject(
            @Valid @RequestBody AssigneeSubjectRequest request) {
        subjectService.assigneeSubject(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new APIResponse("Subject [ %s ] assigned successfully".formatted(request.getSubjectId()),
                        HttpStatus.CREATED.value()));
    }

}
