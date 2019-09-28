package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.dto.ScheduleGenerateDTO;
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

import java.text.ParseException;
import java.util.*;
import javax.print.Doc;

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

    @GetMapping("/{id}/timetable")
    public String giveTimetable(@PathVariable Long id, Model model){
        Doctor doctor = doctorService.findById(id);
        List<Schedule> schedules = scheduleService.findActiveByDoctor(doctor);
        ScheduleGenerateDTO dto = new ScheduleGenerateDTO();

        model.addAttribute("doctor", doctor);
        model.addAttribute("schedules", schedules);
        model.addAttribute("scheduleDTO", dto);
        model.addAttribute("hoursToStart", scheduleService.getHours(23));
        model.addAttribute("durationsHours", scheduleService.getHours(8));

        return "timetable";
    }

    @PostMapping("/{id}/timetable/create")
    public String createTimetable(@PathVariable Long id,
                                  @ModelAttribute ScheduleGenerateDTO dto,
                                  Model model) {
        scheduleService.generateSchedule(dto, doctorService.findById(id));
        return "redirect:/doctor/{id}/timetable";
    }
}
