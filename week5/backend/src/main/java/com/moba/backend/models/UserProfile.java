package com.moba.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
public class UserProfile {

    @Id
    @SequenceGenerator(
            name = "user_profile_id_seq",
            sequenceName = "user_profile_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_profile_id_seq"
    )
    private Integer id;

    @Column(nullable = false)
    private String full_name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone_number;

    private String gender; // stored as plain string

    @ElementCollection
    @CollectionTable(
            name = "user_profile_roles",
            joinColumns = @JoinColumn(name = "user_profile_id")
    )
    @Column(name = "role")
    private List<String> roles = new ArrayList<>();

    private LocalDate date_of_birth;

    @Embedded
    private Address address;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    private Boolean enabled;

    @Lob
    private byte[] profile_picture;

    @Version
    private Integer version;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserData user;

    public UserProfile(){}

    public UserProfile(
            String full_name,
            String email,
            String gender,
            LocalDate date_of_birth) {
        this.full_name = full_name;
        this.email = email;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
    }
}
