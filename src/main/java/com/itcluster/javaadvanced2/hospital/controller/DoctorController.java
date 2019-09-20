package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.repository.DoctorRepository;
import com.itcluster.javaadvanced2.hospital.service.DoctorService;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("/search")
    public String getSearch(){
        return "search";
    }

    @GetMapping("/search")
    public String findDoctorsBySurname(@RequestParam String surname, Model model){
        List<Doctor> doctorsBySurname = doctorService.findBySurname(surname);
        model.addAttribute("doctors",doctorsBySurname);
        return "staff";
    }
}
