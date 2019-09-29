package com.itcluster.javaadvanced2.hospital.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

@Data
@NoArgsConstructor
public class PasswordCheckingDTO {
    private String passwordOld;
    private String passwordRawFir;
    private String passwordRawSec;
}
