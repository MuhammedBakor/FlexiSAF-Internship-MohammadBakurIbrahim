package com.moba.backend.services;

import com.moba.backend.dataObjectTransfers.GradeDTO;
import com.moba.backend.dataObjectTransfers.GradeDTOMapper;
import com.moba.backend.dataObjectTransfers.RoleDTO;
import com.moba.backend.dataObjectTransfers.RoleDTOMapper;
import com.moba.backend.exceptions.DuplicateResourceException;
import com.moba.backend.exceptions.ResourceConflictException;
import com.moba.backend.exceptions.ResourceNotFoundException;
import com.moba.backend.models.*;
import com.moba.backend.repositories.*;
import com.moba.backend.requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleDTOMapper roleDTOMapper;
    private final UserRepository userRepository;

    RoleService(RoleRepository roleRepository, RoleDTOMapper roleDTOMapper, UserRepository userRepository){
        this.roleRepository = roleRepository;
        this.roleDTOMapper = roleDTOMapper;
        this.userRepository = userRepository;
    }

    public void createRole(AddRoleRequest request) {

        if (roleRepository.existsByRoleName(request.getRole_name().toUpperCase())) {
            throw new DuplicateResourceException("Role already exists");
        }

        Role role = new Role(request.getRole_name().toUpperCase());

        roleRepository.save(role);

    }

    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleDTOMapper)
                .toList();
    }

    public RoleDTO getRoleById(Integer roleId) {
       return roleRepository.findById(roleId)
                .map(roleDTOMapper)
               .orElseThrow(() -> new ResourceNotFoundException(
                       "Role with id [ %s ] not found".formatted(roleId)));
    }

    public void updateRole(Integer roleId, RoleUpdateRequest updateRequest) {
        Role roleToUpdate = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Role with id [%s] not found".formatted(roleId)));


        roleToUpdate.setRole_name(updateRequest.getRole_name().toUpperCase());
        roleRepository.save(roleToUpdate);

    }

    public void deleteRole(Integer roleId) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cannot delete. Role with id [ %s ] not found".formatted(roleId)));

        if (role.getUsers() != null && !role.getUsers().isEmpty()) {
            throw new ResourceConflictException(
                    "Cannot delete role '" + role.getRole_name() + "' because it is assigned to existing users."
            );
        }

        roleRepository.deleteById(roleId);
    }

    public void removeUser(RemoveRoleUser removeRequest) {

        UserData userToRemove = userRepository.findById(removeRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cannot remove. User with id [ %s ] not found".formatted(removeRequest.getUserId())));

        Role role = roleRepository.findById(removeRequest.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cannot remove. Role with id [ %s ] not found".formatted(removeRequest.getRoleId())));


        if (!role.getUsers().contains(userToRemove)) {
            throw new ResourceConflictException(
                    "User with ID [%s] is not assigned to Role [%s]"
                            .formatted(userToRemove.getId(), role.getRole_name())
            );
        }

        role.getUsers().remove(userToRemove);
        userToRemove.getRoles().remove(role);

        userRepository.save(userToRemove);
        roleRepository.save(role);
    }
}
