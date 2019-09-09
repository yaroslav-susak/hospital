package com.javaadvanced2.hospital.controller;

import com.javaadvanced2.hospital.model.Doctor;
import com.javaadvanced2.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UIController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("/")
    public String homePage(){
        return "homepage";
    }

    @GetMapping("/doctor-info/{id}")
    public String doctorById(@PathVariable Long id, Model model){
        Doctor doctor = doctorService.findById(id);
        model.addAttribute("doctor", doctor);
        return "doctor";
    }

    @GetMapping("/staff")
    public String getStaff(Model model){
        List<Doctor> allDoctors = doctorService.findAll();
        model.addAttribute("doctors", allDoctors);
        return "staff";
    }
}
