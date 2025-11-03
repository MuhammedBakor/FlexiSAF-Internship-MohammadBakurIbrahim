package com.moba.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Subject {
    @Id
    @SequenceGenerator(
            name = "subject_id_seq",
            sequenceName = "subject_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subject_id_seq"
    )
    private Integer id;

    @Column(nullable = false)
    private String title;

    @ManyToMany(mappedBy = "subjects")
    private Set<Teacher> teachers;

    @OneToMany(mappedBy = "subject")
    private List<Grade> grades;

    public Subject(String title) {
        this.title = title;
    }

}