package com.moba.backend.dataObjectTransfers;

import com.moba.backend.models.Role;
import com.moba.backend.models.UserData;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserDTOMapper implements Function<UserData, UserDTO> {

    @Override
    public UserDTO apply(UserData user) {
        return new UserDTO(
                user.getId(),
                user.getFull_name(),
                user.getEmail(),
                user.getGender(),
                user.getDate_of_birth(),
                user.getRoles()
                        .stream()
                        .map(Role::getRole_name)
                        .collect(Collectors.toSet())
        );
    }
}
