package com.moba.backend.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Teacher {
    @Id
    @SequenceGenerator(
            name = "teacher_id_seq",
            sequenceName = "teacher_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_id_seq"
    )
    private Integer id;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private UserData user;

    @ManyToMany() // Unidirectional
    @JoinTable(
            name = "teacher_classes",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id")
    )
    private Set<Classroom> classrooms;

    @ManyToMany() //Bidirectional
    @JoinTable(
            name = "teacher_subjects",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects;

    @OneToMany(mappedBy = "teacher")
    private List<ClassTimeTable> timetable;
}