package com.itcluster.javaadvanced2.hospital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Doctor {
    @Id()
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name="department_id")
    private Department department;
    private String name;
    private String middleName;
    private String surname;
    private String specialization;
    private String qualificationLevel;
    private String photoName;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
