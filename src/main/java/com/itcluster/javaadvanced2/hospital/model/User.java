package com.itcluster.javaadvanced2.hospital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
    private String photo;
    @ManyToMany
    @JoinTable(name = "user_role",  inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
