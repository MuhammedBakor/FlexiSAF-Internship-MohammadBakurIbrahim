package com.moba.backend.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Attendance {
    @Id
    @SequenceGenerator(
            name = "attendance_id_seq",
            sequenceName = "attendance_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "attendance_id_seq"
    )
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private EnumAttendStatus status;

    @ManyToOne(fetch = FetchType.LAZY) // Unidirectional with user
    @JoinColumn(name = "user_id")
    private UserData user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;
}