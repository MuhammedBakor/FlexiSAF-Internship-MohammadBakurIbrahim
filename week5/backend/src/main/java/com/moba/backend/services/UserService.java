package com.moba.backend.services;

import com.moba.backend.models.UserProfile;
import com.moba.backend.repositories.RoleRepository;
import com.moba.backend.repositories.UserRepository;
import com.moba.backend.requests.UserDTO;
import com.moba.backend.requests.UserDTOMapper;
import com.moba.backend.requests.UserRegistrationRequest;
import com.moba.backend.models.Role;
import com.moba.backend.models.UserData;
import com.moba.backend.requests.UserUpdateRequest;
import org.springframework.stereotype.Service;

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

        Role role = roleRepository.findRoleByRole_name(user.role_name());

        UserData newUser
                = new UserData(
                user.full_name(),
                user.email(),
                user.password(),
                user.gender(),
                user.date_of_birth()
        );

        newUser.getRoles().add(role);

        // Create linked profile
        UserProfile newProfile = new UserProfile(
                user.full_name(),
                user.email(),
                user.gender(),
                user.date_of_birth()
        );

        newUser.setProfile(newProfile);
        newProfile.setUser(newUser);

        userRepository.save(newUser);

        System.out.printf("User [%s] and Profile created successfully%n", newUser.getFull_name());
    }

    public UserDTO getUserById(Integer userId) {

        UserDTO user = userRepository.findById(userId)
                .map(userDTOMapper)
                .orElseThrow();

        System.out.printf("User retrieved with id [%s] successfully%n", userId);
        return user;
    }

    public void updateUser(Integer userId,  UserUpdateRequest user) {

        UserData userToUpdate = userRepository.findById(userId)
                .orElseThrow();

        userToUpdate.setFull_name(user.full_name());
        userToUpdate.setEmail(user.email());
        userToUpdate.setPassword(user.password());
        userToUpdate.setDate_of_birth(user.date_of_birth());

        userRepository.save(userToUpdate);
        System.out.println("User updated successfully");
    }

    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
        System.out.println("User deleted successfully");
    }
}
