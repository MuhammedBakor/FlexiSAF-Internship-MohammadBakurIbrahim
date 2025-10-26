package com.moba.backend.dataObjectTransfers;

import java.util.List;

public record RoleDTO(
        int id,
        String title,
        List<String> users
){
}
