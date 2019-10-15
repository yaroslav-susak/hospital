package com.itcluster.javaadvanced2.hospital.service;

import com.itcluster.javaadvanced2.hospital.dto.ScheduleGenerateDTO;
import com.itcluster.javaadvanced2.hospital.exceptions.HoursIntermixException;
import com.itcluster.javaadvanced2.hospital.exceptions.ScheduleNotAvailableException;
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

    @Autowired
    UserService userService;

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
        List<Schedule> activeSchedules = getActiveSchedules(allSchedules);
        Collections.sort(activeSchedules);
        return activeSchedules;
    }

    public List<Schedule> findActiveByUser(User user){
        List<Schedule> allSchedules = findByPatient(user);
        List<Schedule> activeSchedules = getActiveSchedules(allSchedules);
        Collections.sort(activeSchedules);
        return activeSchedules;
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

            List<Schedule> schedulesForDoctor = findByDoctor(doctor);

            hoursConflict(schedulesForDoctor, schedule);

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

    public Schedule findById(Long id){
        return scheduleRepository.findById(id).orElse(null);
    }

    public void setPatientForSchedule(Long scheduleId, Long patientId){
        Schedule schedule = findById(scheduleId);
        User patient = userService.findById(patientId);

        if (scheduleIsFree(schedule, patient)){
            setOrDeletePatient(patient, schedule);
        }
    }

    public void deletePatientFromSchedule(Long scheduleId, Long patientId){
        Schedule schedule = findById(scheduleId);
        setOrDeletePatient(null, schedule);
    }

    public void setOrDeletePatient(User patient, Schedule schedule){
        schedule.setPatient(patient);
        scheduleRepository.save(schedule);
    }

    public boolean scheduleIsFree(Schedule schedule, User patient){
        List<Schedule> schedulesByPatient = findByPatient(patient);
        List<Schedule> schedulesByPatientAndByDoctor = new ArrayList<>();

        for (Schedule sc : schedulesByPatient) {
            if(sc.getDoctor().equals(schedule.getDoctor())){
                schedulesByPatientAndByDoctor.add(sc);
            }
        }

        return !onSameDay(schedulesByPatientAndByDoctor, schedule) &&
                !hoursConflict(schedulesByPatient, schedule);
    }

    public boolean onSameDay(List<Schedule> schedules, Schedule schedule){
        for (Schedule sc : schedules){
            if(sameDay(sc.getStart(), schedule.getStart())){
                throw new ScheduleNotAvailableException();
            }
        }
        return false;
    }

    public boolean sameDay(Date date1, Date date2){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }

    public boolean hoursConflict(List<Schedule> schedules, Schedule schedule){
        for (Schedule sc : schedules){
            if(hoursIntermix(sc.getStart(), sc.getEnd(), schedule.getStart(), schedule.getEnd())){
                throw new HoursIntermixException();
            }
        }
        return false;
    }

    public boolean hoursIntermix(Date firstStart, Date firstEnd, Date secondStart, Date secondEnd){
        return (firstStart.after(secondStart) && firstStart.before(secondEnd)) ||
                (firstEnd.after(secondStart) && firstEnd.before(secondEnd)) ||
                (secondStart.after(firstStart) && secondStart.before(firstEnd)) ||
                (secondEnd.after(firstStart) && secondEnd.before(firstEnd)) ||
                (firstStart.equals(secondStart) && firstEnd.equals(secondEnd));
    }
}
