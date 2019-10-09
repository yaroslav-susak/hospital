package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.dto.ReviewDTO;
import com.itcluster.javaadvanced2.hospital.dto.ScheduleGenerateDTO;
import com.itcluster.javaadvanced2.hospital.exceptions.BannedUserException;
import com.itcluster.javaadvanced2.hospital.model.*;
import com.itcluster.javaadvanced2.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@Controller
public class UniversalController {
    @Autowired
    DoctorService doctorService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ScheduleService scheduleService;

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

    @GetMapping("/doctor-info/{id}")
    public String doctorById(@PathVariable Long id, Model model, @ModelAttribute User user){
        Doctor doctor = doctorService.findById(id);
        List<Review> reviews = reviewService.findByDoctor(doctor);
        Collections.reverse(reviews);
        reviewService.userReviewFirst(reviews, user);

        model.addAttribute("doctor", doctor);
        model.addAttribute("reviews", reviews);
        model.addAttribute("reviewDTO", new ReviewDTO());
        model.addAttribute("userAlreadyLeftReview",
                reviewService.userAlreadyLeftReview(reviews, user));

        doctorService.addSearchOptions(model);
        return "doctor";
    }

    @GetMapping("/doctor-info/{id}/timetable")
    public String giveTimetable(@PathVariable Long id, Model model){
        Doctor doctor = doctorService.findById(id);
        List<Schedule> schedules = scheduleService.findActiveByDoctor(doctor);
        ScheduleGenerateDTO dto = new ScheduleGenerateDTO();

        model.addAttribute("doctor", doctor);
        model.addAttribute("schedules", schedules);
        model.addAttribute("scheduleDTO", dto);
        model.addAttribute("hoursToStart", scheduleService.getHours(23));
        model.addAttribute("durationsHours", scheduleService.getHours(8));

        doctorService.addSearchOptions(model);
        return "timetable";
    }
}
