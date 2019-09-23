package com.itcluster.javaadvanced2.hospital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Має бути непорожнім")
    @Email(message = "Неправильний email")
    private String email;

    @NotBlank(message = "Має бути непорожнім")
    private String password;

    @NotBlank(message = "Має бути непорожнім")
    private String firstName;

    @NotBlank(message = "Має бути непорожнім")
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date dateOfRegistration;

    private String photo;

    @ManyToMany
    @JoinTable(name = "user_role",  inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
