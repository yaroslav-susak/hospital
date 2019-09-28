package com.itcluster.javaadvanced2.hospital.service;

import com.itcluster.javaadvanced2.hospital.dto.ScheduleGenerateDTO;
import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.model.Schedule;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;

@Service
@Slf4j
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

    public Schedule save(Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    public void generateSchedule(ScheduleGenerateDTO dto, Doctor doctor) {

        Date date = null;

        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(dto.getDateString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.HOUR, dto.getStartingHour());

        int numberOfSchedules = dto.getLengthOfTheDay() * 60 / dto.getDuration();
        Schedule schedule = null;

        for (int i = 0; i < numberOfSchedules; i++){
            schedule = new Schedule();
            schedule.setStart(cal.getTime());
            cal.add(Calendar.MINUTE, dto.getDuration());
            schedule.setEnd(cal.getTime());
            schedule.setDoctor(doctor);
            scheduleRepository.save(schedule);
        }
    }

    public Set<Integer> getHours(int number){
        Set<Integer> hours = new HashSet<>();
        for (int i = 1; i<=number ; i++) {
            hours.add(i);
        }
        return hours;
    }
}
