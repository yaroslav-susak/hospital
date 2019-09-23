package com.itcluster.javaadvanced2.hospital.service;

import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.model.Schedule;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public List<Schedule> findByDoctor(Doctor doctor){
        return scheduleRepository.findByDoctor(doctor);
    }

    public List<Schedule> findByPatient(User user){
        return scheduleRepository.findByPatient(user);
    }

    private List<Schedule> getActiveSchedules(List<Schedule> schedules){
        List<Schedule> activeSchedules = new ArrayList<>();

        for (Schedule schedule : schedules){
            if(schedule.getEnd().after(new Date())){
                activeSchedules.add(schedule);
            }
        }

        return activeSchedules;
    }

    public List<Schedule> findActiveByDoctor(Doctor doctor){
        List<Schedule> allSchedules = findByDoctor(doctor);
        return getActiveSchedules(allSchedules);
    }

    public List<Schedule> findActiveByUser(User user){
        List<Schedule> allSchedules = findByPatient(user);
        return getActiveSchedules(allSchedules);
    }
}
