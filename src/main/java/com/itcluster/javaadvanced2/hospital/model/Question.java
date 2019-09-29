package com.itcluster.javaadvanced2.hospital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
public class Question {
    @Id()
    @GeneratedValue
    private Long id;
    private String question;
    private String answer;

}
