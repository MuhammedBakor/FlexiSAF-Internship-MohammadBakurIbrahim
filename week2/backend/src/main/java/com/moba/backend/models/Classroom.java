package com.moba.backend.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Classroom {
    @Id
    @SequenceGenerator(
            name = "classroom_id_seq",
            sequenceName = "classroom_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "classroom_id_seq"
    )
    private Integer id;

    @Column(nullable = false)
    private String class_name;

    @OneToMany(mappedBy = "classroom") // Bidirectional
    private Set<Student> students;

    @OneToMany(mappedBy = "classroom")
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "classroom")
    private List<ClassTimeTable> timetable;
    
}