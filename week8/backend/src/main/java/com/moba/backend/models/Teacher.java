package com.moba.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @CreationTimestamp
    private LocalDateTime assigned_date;

    @UpdateTimestamp
    private LocalDateTime updated_date;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private UserData user;

    @ManyToMany() //Bidirectional
    @JoinTable(
            name = "teacher_subjects",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects;


}