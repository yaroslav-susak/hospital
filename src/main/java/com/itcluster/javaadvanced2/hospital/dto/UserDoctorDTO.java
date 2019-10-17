package com.itcluster.javaadvanced2.hospital.dto;

import com.itcluster.javaadvanced2.hospital.model.Department;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDoctorDTO {
    String name;
    String surname;
    String middleName;
    String email;
    String password;
    String qualification;
    String photo;
    String specialization;
    int departmentId;

}
