package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.model.Schedule;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.service.DepartmentService;
import com.itcluster.javaadvanced2.hospital.service.DoctorService;
import com.itcluster.javaadvanced2.hospital.service.ScheduleService;
import com.itcluster.javaadvanced2.hospital.service.UserService;
import org.hibernate.validator.constraints.EAN;
import com.itcluster.javaadvanced2.hospital.service.ScheduleService;
import com.itcluster.javaadvanced2.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.print.Doc;
import java.util.Date;
import java.util.HashSet;

@Controller
@RequestMapping("/doctor")

public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    UserService userService;

    @ModelAttribute("user")
    public User activeUser(Authentication authentication) {
        return userService.findUserByEmail(authentication.getName()).get();
    }

    @GetMapping("/search")
    public String findDoctorsByQualificationLevel(@RequestParam(name = "lvl", required = false) String qualification,
                                                  @RequestParam(name = "dep", required = false) String department,
                                                  @RequestParam(name = "sur", required = false) String surname,
                                                  Model model) {

        HashSet<Doctor> doctors = new HashSet<>(doctorService.findAll());

        if (qualification != null){
            doctorService.removeDifferent(doctors, doctorService.findByQualificationLevel(qualification));
        }
        if(department!=null) {
            doctorService.removeDifferent(doctors, doctorService.findByDepartment(departmentService.findByName(
                    department)));
        }
        if(surname!=null) {
            doctorService.removeDifferent(doctors, doctorService.findBySurname(surname));
        }

        model.addAttribute("doctors",doctors);
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
