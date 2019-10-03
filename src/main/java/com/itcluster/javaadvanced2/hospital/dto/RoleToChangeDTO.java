package com.itcluster.javaadvanced2.hospital.dto;


import com.itcluster.javaadvanced2.hospital.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleToChangeDTO {
    private String role;
    private String email;
}
