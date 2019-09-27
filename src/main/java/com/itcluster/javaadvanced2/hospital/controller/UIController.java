package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.model.Department;
import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.service.DepartmentService;
import com.itcluster.javaadvanced2.hospital.service.DoctorService;
import com.itcluster.javaadvanced2.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class UIController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    DepartmentService departmentService;

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

    @GetMapping("/doctor/search")
    public String findDoctorsByQualificationLevel(@RequestParam(name = "lvl", required = false) String qualification,
                                                  @RequestParam(name = "dep", required = false) String department,
                                                  @RequestParam(name = "sur", required = false) String surname,
                                                  Model model) {

        Set<Doctor> doctors = new HashSet<>(doctorService.findAll());

        if (qualification != null){
            doctors.retainAll(doctorService.findByQualificationLevel(qualification));
        }

        if(department!=null) {
            doctors.retainAll(doctorService.findByDepartment(departmentService.findByName(department)));
        }

        if(surname!=null) {
            doctors.retainAll(doctorService.findBySurname(surname));
        }

        model.addAttribute("doctors",doctors);
        return "staff";
    }

    @GetMapping("/departments")
    public String getDepartmentsList(Model model){
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments",departments);
        return "departments";
    }
}
