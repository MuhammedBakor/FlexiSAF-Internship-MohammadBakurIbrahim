package com.moba.backend.services;

import com.moba.backend.exceptions.ResourceNotFoundException;
import com.moba.backend.models.Role;
import com.moba.backend.models.UserData;
import com.moba.backend.repositories.RoleRepository;
import com.moba.backend.repositories.UserRepository;
import com.moba.backend.dataObjectTransfers.UserDTO;
import com.moba.backend.dataObjectTransfers.UserDTOMapper;
import com.moba.backend.requests.UserRegistrationRequest;
import com.moba.backend.requests.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDTOMapper userDTOMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public void addUser(UserRegistrationRequest user) {

        if (user.getRole_name().equals("student".toUpperCase())
                || user.getRole_name().equals("teacher".toUpperCase())) {
            throw new IllegalStateException("Cannot assignee the role here");
        }

        Role role = roleRepository.findRoleByName(user.role_name().toUpperCase())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role [%s] not found".formatted(user.role_name())));

        UserData newUser = new UserData(
                user.getFull_name(),
                user.getEmail(),
                user.getPassword(),
                user.getGender(),
                user.getDate_of_birth()
        );
        newUser.getRoles().add(role);
        newUser.setEnabled(true);


        userRepository.save(newUser);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDTOMapper)
                .toList();
    }

    public UserDTO getUserById(Integer userId) {
        return userRepository.findById(userId)
                .map(userDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    public void updateUser(Integer userId, UserUpdateRequest user) {
        UserData userToUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        userToUpdate.setFullName(user.getFull_name());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setDate_of_birth(user.getDate_of_birth());

        userRepository.save(userToUpdate);
    }

    public void deleteUserById(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Cannot delete. User not found with id " + userId);
        }
        userRepository.deleteById(userId);
    }

    public List<UserDTO> searchUsers(String keyword) {
        return userRepository.findByFullNameContainingIgnoreCase(keyword)
                .stream()
                .map(userDTOMapper)
                .toList();
    }
}
