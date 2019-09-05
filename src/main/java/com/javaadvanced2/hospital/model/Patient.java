package com.javaadvanced2.hospital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Patient {
    @Id()
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private Sex sex;
    private Date dateOfBirth;
    private int age;
    private Date dateOfRegistration;
    private Address address;
    private String telephoneNumber;

    enum Sex {
        MALE,
        FEMALE
    }
}
