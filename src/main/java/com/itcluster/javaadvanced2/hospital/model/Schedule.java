package com.itcluster.javaadvanced2.hospital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.print.Doc;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Schedule {
    @Id()
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @OneToOne
    @JoinColumn(name = "patient_id")
    private User patient;
    private Date start;
    private Date end;
}
