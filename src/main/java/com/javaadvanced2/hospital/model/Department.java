package com.javaadvanced2.hospital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Department {
    @Id()
    @GeneratedValue
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
}
