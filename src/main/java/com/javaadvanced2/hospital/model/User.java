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
public class User {
    @Id()
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfRegistration;
}
