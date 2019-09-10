package com.javaadvanced2.hospital.model;

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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="department_id", nullable = false)
    private Department department;
    private String name;
    private String middleName;
    private String surname;
    private String specialization;
    private String qualificationLevel;
    private String photoName;
}
