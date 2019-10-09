package com.itcluster.javaadvanced2.hospital.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
public class ScheduleNotAvailableException extends RuntimeException {
    public ScheduleNotAvailableException() {
        super("У вас є один запис на цю дату до цього лікаря або у вас є запис на цю годину до іншого лікаря. Перевірте свої записи");
    }
}