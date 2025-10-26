package com.moba.backend.dataObjectTransfers;

import com.moba.backend.models.Role;
import com.moba.backend.models.Subject;
import com.moba.backend.models.Teacher;
import com.moba.backend.models.UserData;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RoleDTOMapper implements Function<Role, RoleDTO> {

    @Override
    public RoleDTO apply(Role role) {

        return new RoleDTO(
                role.getId(),
                role.getRole_name(),
                role.getUsers().stream()
                        .map(UserData::getFull_name)
                        .toList()
        );
    }
}
