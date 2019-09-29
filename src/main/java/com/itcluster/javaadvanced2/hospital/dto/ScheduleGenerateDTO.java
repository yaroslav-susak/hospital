package com.itcluster.javaadvanced2.hospital.dto;

import com.itcluster.javaadvanced2.hospital.model.Doctor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
public class ScheduleGenerateDTO {
    private String dateString;
    private int startingHour;
    private int lengthOfTheDay;
    private int duration;
    private Doctor doctor;
}
