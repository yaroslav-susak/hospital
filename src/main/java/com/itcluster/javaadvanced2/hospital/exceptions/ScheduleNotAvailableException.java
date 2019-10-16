package com.itcluster.javaadvanced2.hospital.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
public class ScheduleNotAvailableException extends RuntimeException {
    public ScheduleNotAvailableException() {
        super("У вас вже є один запис на цю дату до цього лікаря");
    }
}