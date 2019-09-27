package com.itcluster.javaadvanced2.hospital.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

@Data
public class PasswordCheckingDTO {
    private String passwordOld;
    private String passwordRawFir;
    private String passwordRawSec;
}
