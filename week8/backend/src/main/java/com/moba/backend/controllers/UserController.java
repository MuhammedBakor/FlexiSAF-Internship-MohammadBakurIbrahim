package com.moba.backend.controllers;

import com.moba.backend.dataObjectTransfers.UserDTO;
import com.moba.backend.requests.UserRegistrationRequest;
import com.moba.backend.requests.UserUpdateRequest;
import com.moba.backend.responses.APIResponse;
import com.moba.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<APIResponse> registerUser(
            @Valid @RequestBody UserRegistrationRequest request) {

        log.info("Creating new user with email: {}", request.getEmail());
        userService.addUser(request);

        APIResponse response = new APIResponse(
                "User created successfully",
                HttpStatus.CREATED.value()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<APIResponse> getAllUsers() {
        return ResponseEntity.ok(
                new APIResponse("All Users fetched", HttpStatus.OK.value(),
                        userService.getAllUsers()));

    }

    // Get one user
    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUser(@PathVariable Integer userId) {
        UserDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(
                new APIResponse(
                        "User retrieved successfully",
                        HttpStatus.OK.value(),
                        user));
    }

    // Update user
    @PutMapping("/{userId}")
    public ResponseEntity<APIResponse> updateUser(
            @PathVariable Integer userId,
            @Valid @RequestBody UserUpdateRequest request) {
        userService.updateUser(userId, request);
        return ResponseEntity.ok(new APIResponse(
                "User updated successfully", HttpStatus.OK.value()));
    }

    // Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok(new APIResponse(
                "User deleted successfully", HttpStatus.OK.value()));
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse> searchUsers(@RequestParam String keyword) {
        List<UserDTO> users = userService.searchUsers(keyword);

        APIResponse response = new APIResponse(
                "Search results for keyword: " + keyword,
                HttpStatus.OK.value(),
                users
        );

        return ResponseEntity.ok(response);
    }
}
