package com.itcluster.javaadvanced2.hospital.controller;


import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.service.DepartmentService;
import com.itcluster.javaadvanced2.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    DepartmentService departmentService;

//    @GetMapping("/search")
//    public String getSearch(){
//        return "search";
//    }

//    @GetMapping("/search/{surname}")
//    public String findDoctorsBySurname(@PathVariable String surname, Model model){
//        List<Doctor> doctorsBySurname = doctorService.findBySurname(surname);
//        model.addAttribute("doctors",doctorsBySurname);
//        return "staff";
//    }

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

//    @GetMapping("/search/{Department}")
//    public String findDoctorsByDepartment(@PathVariable String Department, Model model){
//        List<Doctor> doctorsByDepartment = doctorService.findBySurname(Department);
//        model.addAttribute("doctors",doctorsByDepartment);
//        return "staff";
//    }


}
