package com.moba.backend.services;

import com.moba.backend.dataObjectTransfers.UserDTO;
import com.moba.backend.dataObjectTransfers.UserDTOMapper;
import com.moba.backend.exceptions.ResourceNotFoundException;
import com.moba.backend.models.Role;
import com.moba.backend.models.UserData;
import com.moba.backend.repositories.RoleRepository;
import com.moba.backend.repositories.UserRepository;
import com.moba.backend.requests.UserRegistrationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserDTOMapper userDTOMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUser_ShouldSaveUser_WhenRoleExists() {
        // Given
        UserRegistrationRequest request = new UserRegistrationRequest(
                "MoBa",
                "moba@test.com",
                "12345",
                "MALE",
                LocalDate.of(2000, 6, 20),
                "ADMIN"
        );

        Role adminRole = new Role("ADMIN");
        when(roleRepository.findRoleByName("ADMIN"))
                .thenReturn(Optional.of(adminRole));

        // When
        userService.addUser(request);

        // Then
        verify(userRepository, times(1)).save(any(UserData.class));
    }

    @Test
    void addUser_ShouldThrow_WhenRoleNotFound() {
        UserRegistrationRequest request = new UserRegistrationRequest(
                "MoBa",
                "moba@test.com",
                "12345",
                "MALE",
                LocalDate.of(2000, 6, 20),
                "ADMIN"
        );

        when(roleRepository.findRoleByName("ADMIN"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.addUser(request))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void getUserById_ShouldReturnDTO() {
        // Given
        UserData user = new UserData("MoBa", "x@test.com",
                "12345", "MALE", LocalDate.of(2000,1,1));

        Integer id = 9;
        user.setId(id);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userDTOMapper.apply(user)).thenReturn(new UserDTO(
               id, "MoBa", "x@test.com",
                "MALE", LocalDate.of(2000,1,1), Set.of("ADMIN")));

        // When
        UserDTO result = userService.getUserById(1);

        // Then
        assertThat(result.email()).isEqualTo("x@test.com");
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void getUserById_ShouldThrow_WhenNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById(1))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void deleteUser_ShouldCallRepository_WhenExists() {
        when(userRepository.existsById(1)).thenReturn(true);

        userService.deleteUserById(1);

        verify(userRepository).deleteById(1);
    }

    @Test
    void deleteUser_ShouldThrow_WhenNotExists() {
        when(userRepository.existsById(1)).thenReturn(false);

        assertThatThrownBy(() -> userService.deleteUserById(1))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void searchUsers_ShouldReturnMappedResults() {
        UserData user = new UserData("MoBa", "x@test.com",
                "11111", "MALE", LocalDate.of(2000,1,1));

        Integer id = 9;
        user.setId(id);

        when(userRepository.findByFullNameContainingIgnoreCase("oba"))
                .thenReturn(List.of(user));
        when(userDTOMapper.apply(user))
                .thenReturn(new UserDTO(
                        id, "MoBa", "x@test.com",
                        "MALE", LocalDate.of(2000,1,1), Set.of("ADMIN"))
                );

        List<UserDTO> result = userService.searchUsers("oba");

        assertThat(result).hasSize(1);
    }
}