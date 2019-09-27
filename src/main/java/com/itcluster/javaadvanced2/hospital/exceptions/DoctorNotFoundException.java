package com.itcluster.javaadvanced2.hospital.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Лікаря з таким id не існує")
@Slf4j
public class DoctorNotFoundException extends IllegalArgumentException {
    public DoctorNotFoundException(Long id) {
        super("Не існує лікаря з id: " + id);
    }
}