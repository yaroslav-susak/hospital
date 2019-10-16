package com.itcluster.javaadvanced2.hospital.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
public class HoursIntermixException extends RuntimeException {
    public HoursIntermixException() {
        super("Є записи на цю годину");
    }
}