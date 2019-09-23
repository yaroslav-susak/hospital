package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.model.Schedule;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.repository.DoctorRepository;
import com.itcluster.javaadvanced2.hospital.service.DoctorService;
import com.itcluster.javaadvanced2.hospital.service.ScheduleService;
import com.itcluster.javaadvanced2.hospital.service.UserService;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    UserService userService;

    @ModelAttribute("user")
    public User activeUser(Authentication authentication) {
        return userService.findUserByEmail(authentication.getName()).get();
    }

    @GetMapping("/search")
    public String getSearch(){
        return "search";
    }

    @GetMapping("/search/{surname}")
    public String findDoctorsBySurname(@PathVariable String surname, Model model){
        List<Doctor> doctorsBySurname = doctorService.findBySurname(surname);
        model.addAttribute("doctors",doctorsBySurname);
        return "staff";
    }

    @GetMapping("/{id}/timetable")
    public String giveTimetable(@PathVariable Long id, Model model){
        Doctor doctor = doctorService.findById(id);
        List<Schedule> schedules = scheduleService.findActiveByDoctor(doctor);
        model.addAttribute("doctor", doctor);
        model.addAttribute("schedules",schedules);
        return "timetable";
    }

    @PostMapping("/{id}/timetable/create")
    public String createTimetable(@PathVariable Long id, Model model) {
        Date date = new Date();
        return "forward:/{id}/timetable";
    }
}
