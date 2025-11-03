package com.moba.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.moba.backend.dataObjectTransfers.UserDTO;
import com.moba.backend.exceptions.GlobalExceptionHandler;
import com.moba.backend.requests.UserRegistrationRequest;
import com.moba.backend.requests.UserUpdateRequest;
import com.moba.backend.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void registerUser_ShouldReturnCreated() throws Exception {
        UserRegistrationRequest request = new UserRegistrationRequest(
                "MoBa",
                "test@test.com",
                "12345656",
                "MALE",
                LocalDate.of(2000,1,1),
                "ADMIN"
        );

        doNothing().when(userService).addUser(request);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("User created successfully"));
    }

    @Test
    void getAllUsers_ShouldReturnOK() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("All Users fetched"));
    }

    @Test
    void getUserById_ShouldReturnUser() throws Exception {
        Integer id = 9;
        UserDTO dto = new UserDTO(
                id, "MoBa", "test@test.com",
                "MALE", LocalDate.of(2000,1,1), Set.of("ADMIN"));
        when(userService.getUserById(1)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value("test@test.com"));
    }

    @Test
    void updateUser_ShouldReturnOK() throws Exception {
        UserUpdateRequest updateRequest = new UserUpdateRequest(
                "UpdatedName",
                "updated@test.com",
                "123456",
                LocalDate.of(1999, 1, 1)
        );

        doNothing().when(userService).updateUser(1, updateRequest);

        mockMvc.perform(put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User updated successfully"));
    }

    @Test
    void deleteUser_ShouldReturnOK() throws Exception {
        doNothing().when(userService).deleteUserById(1);

        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User deleted successfully"));
    }

    @Test
    void searchUsers_ShouldReturnList() throws Exception {
        Integer id = 9;
        List<UserDTO> mockUsers = List.of(
                new UserDTO(
                        id, "MoBa", "test@test.com",
                        "MALE", LocalDate.of(2000,1,1), Set.of("ADMIN"))
        );

        when(userService.searchUsers("MoBa")).thenReturn(mockUsers);

        mockMvc.perform(get("/api/v1/users/search")
                        .param("keyword", "MoBa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Search results for keyword: MoBa"))
                .andExpect(jsonPath("$.data[0].email").value("test@test.com"));
    }

}


