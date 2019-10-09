package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.dto.ReviewDTO;
import com.itcluster.javaadvanced2.hospital.dto.ScheduleGenerateDTO;
import com.itcluster.javaadvanced2.hospital.exceptions.BannedUserException;
import com.itcluster.javaadvanced2.hospital.model.*;
import com.itcluster.javaadvanced2.hospital.service.*;
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
    private DoctorService doctorService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private RoleService roleService;

    @ModelAttribute("user")
    public User activeUser(Authentication authentication) {
        if (authentication != null) {
            User user = userService.findUserByEmail(authentication.getName()).get();
            if (user.isBanned()) {
                throw new BannedUserException();
            } else {
                return user;
            }
        }
        return  null;
    }

    @PostMapping("/{id}/timetable/create")
    public String createTimetable(@PathVariable Long id,
                                  @ModelAttribute ScheduleGenerateDTO dto,
                                  Model model) {
        scheduleService.generateSchedule(dto, doctorService.findById(id));
        return "redirect:/doctor-info/{id}/timetable";
    }

    @GetMapping("/{id}/timetable/schedule")
    public String setUserForSchedule(@RequestParam(name="scheduleId") Long scheduleId,
                                     @RequestParam(name="patientId") Long patientId,
                                     Model model,
                                     @PathVariable Long id){
        scheduleService.setPatientForSchedule(scheduleId, patientId);
        return "redirect:/doctor-info/{id}/timetable";
    }

    @GetMapping("/{id}/timetable/schedule/delete")
    public String deleteUserFromSchedule(@RequestParam(name="scheduleId") Long scheduleId,
                                     @RequestParam(name="patientId") Long patientId,
                                     Model model,
                                     @PathVariable Long id){
        scheduleService.deletePatientFromSchedule(scheduleId, patientId);
        return "redirect:/doctor-info/{id}/timetable";
    }
}
