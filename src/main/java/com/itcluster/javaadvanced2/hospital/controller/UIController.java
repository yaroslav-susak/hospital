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
import java.util.List;

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

    @GetMapping("/doctor-info")
    public String doctorById(@RequestParam Long doctorId, Model model){
        Doctor doctor = doctorService.findById(doctorId);
        model.addAttribute("doctor", doctor);
        return "doctor";
    }

    @GetMapping("/staff")
    public String getStaff(Model model){
        List<Doctor> allDoctors = doctorService.findAll();
        model.addAttribute("doctors", allDoctors);
        model.addAttribute("status","all");
        return "staff";
    }

    @GetMapping("/department/")
    public String getDepartment(Model model, @RequestParam Long departmentId){
        Department department = departmentService.findById(departmentId);
        List<Doctor> thisDepartmentDoctors = doctorService.findByDepartment(department);
        model.addAttribute("doctors", thisDepartmentDoctors);
        model.addAttribute("department",department);
        model.addAttribute("status","department");
        return "staff";
    }

    @GetMapping("/departments")
    public String getDepartmentsList(Model model){
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments",departments);
        return "departments";
    }
}
