package com.moba.backend.controllers;

import com.moba.backend.requests.*;
import com.moba.backend.responses.APIResponse;
import com.moba.backend.services.GradeService;
import com.moba.backend.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping()
    public ResponseEntity<APIResponse> createRole(
            @Valid @RequestBody AddRoleRequest addRequest) {
        roleService.createRole(addRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new APIResponse(
                        "Role [ %s ] created successfully".formatted(addRequest.getRole_name().toUpperCase()),
                        HttpStatus.CREATED.value()));
    }

    @GetMapping
    public ResponseEntity<APIResponse> getAllRoles() {
        return ResponseEntity.ok(
                new APIResponse("All Roles fetched", HttpStatus.OK.value(),
                roleService.getAllRoles()));
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<APIResponse> getRole(@PathVariable Integer roleId) {
        return ResponseEntity.ok(
                new APIResponse("Role with id [ %s ] fetched".formatted(roleId), HttpStatus.OK.value(),
                        roleService.getRoleById(roleId)));
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<APIResponse> updateRole(
            @PathVariable Integer roleId, @Valid @RequestBody RoleUpdateRequest updateRequest) {
        roleService.updateRole(roleId, updateRequest);
        return ResponseEntity.ok(
                new APIResponse(
                        "Role with id [ %s ] updated".formatted(roleId), HttpStatus.OK.value()));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<APIResponse> deleteRole(@PathVariable Integer roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.ok(new APIResponse("Role deleted", HttpStatus.OK.value()));
    }

    @DeleteMapping()
    public ResponseEntity<APIResponse> removeUser(
            @Valid @RequestBody RemoveRoleUser removeRequest) {
        roleService.removeUser(removeRequest);
        return ResponseEntity.ok(new APIResponse("User Removed", HttpStatus.OK.value()));
    }
}
