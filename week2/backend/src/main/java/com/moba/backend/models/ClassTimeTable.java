package com.moba.backend.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ClassTimeTable {
    @Id
    @SequenceGenerator(
            name = "class_time_table_id_seq",
            sequenceName = "class_time_table_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "class_time_table_id_seq"
    )
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime start;

    @Column(nullable = false)
    private LocalDateTime end;

    @ManyToOne()
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @ManyToOne()
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne()
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

}