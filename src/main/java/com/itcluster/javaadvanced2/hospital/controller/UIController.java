package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.model.Department;
import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.service.DepartmentService;
import com.itcluster.javaadvanced2.hospital.service.DoctorService;
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

    @GetMapping("/staff")
    public String getStaff(Model model){
        List<Doctor> allDoctors = doctorService.findAll();
        model.addAttribute("doctors", allDoctors);
        return "staff";
    }

    @GetMapping("/department/{id}")
    public String getDepartment(Model model, @PathVariable Long id){
        Department department = departmentService.findById(id);
        List<Doctor> thisDepartmentDoctors = doctorService.findByDepartment(department);
        model.addAttribute("thisDepartmentDoctors",thisDepartmentDoctors);
        model.addAttribute("department",department);
        return "department";
    }

    @GetMapping("/departments")
    public String getDepartmentsList(Model model){
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments",departments);
        return "departments";
    }
}
