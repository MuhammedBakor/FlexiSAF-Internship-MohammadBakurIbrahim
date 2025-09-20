package com.moba.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @SequenceGenerator(
            name = "role_id_seq",
            sequenceName = "role_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_id_seq"
    )
    private Integer id;

    private EnumRoleName role_name;

    @ManyToMany(mappedBy = "roles")
    private List<UserData> users;
}