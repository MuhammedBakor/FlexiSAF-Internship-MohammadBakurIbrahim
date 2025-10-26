package com.moba.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private String grade_type;

    @CreationTimestamp
    private LocalDateTime date_recorded;

    @UpdateTimestamp
    private LocalDateTime updated_date;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne()
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Grade(float score, String grade_type) {
        this.score = score;
        this.grade_type = grade_type;
    }

}