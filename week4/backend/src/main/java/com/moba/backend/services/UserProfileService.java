package com.moba.backend.services;

import com.moba.backend.models.UserProfile;
import com.moba.backend.repositories.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public List<UserProfile> getAll() {
        return userProfileRepository.findAll();
    }

    public Optional<UserProfile> getById(Integer id) {
        return userProfileRepository.findById(id);
    }

    public UserProfile createUserProfile(UserProfile user) {
        return userProfileRepository.save(user);
    }

    public void deleteUserProfile(Integer id) {
        userProfileRepository.deleteById(id);
    }

    public List<UserProfile> getByRole(String role) {
        return userProfileRepository.findByRole(role);
    }
}
