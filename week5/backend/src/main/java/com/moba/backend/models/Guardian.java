package com.moba.backend.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Guardian {
    @Id
    @SequenceGenerator(
            name = "guardian_id_seq",
            sequenceName = "guardian_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "guardian_id_seq"
    )
    private Integer id;

    @Column(nullable = false)
    private String full_name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone_number;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate date_of_birth;

    @OneToMany(
            mappedBy = "guardian",
            fetch = FetchType.LAZY) // Bidirectional to fetch all guardians' students
    private List<Student> students;
}