package com.moba.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Grade {
    @Id
    @SequenceGenerator(
            name = "grade_id_seq",
            sequenceName = "grade_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "grade_id_seq"
    )
    private Integer id;

    @Column(nullable = false)
    private float score;

    @Column(nullable = false)
    private EnumGradeType grade_type;

    @Column(nullable = false)
    private LocalDate date_recorded;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne()
    @JoinColumn(name = "subject_id")
    private Subject subject;

}