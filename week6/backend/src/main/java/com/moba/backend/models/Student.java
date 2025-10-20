package com.moba.backend.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_id_seq",
            sequenceName = "student_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_id_seq"
    )
    private Integer id;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private UserData user;

    @OneToMany(mappedBy = "student")
    private List<Grade> grades;
}