package com.moba.backend.controllers;

import com.moba.backend.requests.UserDTO;
import com.moba.backend.requests.UserRegistrationRequest;
import com.moba.backend.requests.UserUpdateRequest;
import com.moba.backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {

        userService.addUser(request);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable("userId") Integer userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public void updateUser(
            @PathVariable("userId") Integer userId, @RequestBody UserUpdateRequest request) {
        userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUserById(userId);
    }
}
