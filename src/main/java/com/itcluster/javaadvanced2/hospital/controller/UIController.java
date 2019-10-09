package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.dto.ReviewDTO;
import com.itcluster.javaadvanced2.hospital.model.*;
import com.itcluster.javaadvanced2.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    private DoctorService doctorService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private DiseaseService diseaseService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homePage(Model model){
        doctorService.addSearchOptions(model);
        return "homepage";
    }

    @GetMapping("/search")
    public String findDoctorsByQualificationLevel(@RequestParam(name = "lvl", required = false) String qualification,
                                                  @RequestParam(name = "dep", required = false) String department,
                                                  @RequestParam(name = "sur", required = false) String surname,
                                                  Model model) {

        Set<Doctor> doctors = new HashSet<>(doctorService.findAll());

        if (qualification != null && !qualification.equals("")){
            doctors.retainAll(doctorService.findByQualificationLevel(qualification));
        }

        if(department!=null && !department.equals("")) {
            doctors.retainAll(doctorService.findByDepartment(departmentService.findByName(department)));
        }

        if(surname!=null && !surname.equals("")) {
            doctors.retainAll(doctorService.findBySurname(surname));
        }

        model.addAttribute("doctors",doctors);

        doctorService.addSearchOptions(model);
        return "staff";
    }

    @GetMapping("/departments")
    public String getDepartmentsList(Model model){
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments",departments);

        doctorService.addSearchOptions(model);
        return "departments";
    }

    @GetMapping("/faq")
    public String getFaqList(Model model){
        List<Question> questions = questionService.findAll();
        model.addAttribute("questions",questions);

        doctorService.addSearchOptions(model);
        return "faq";
    }

    @GetMapping("/diseases")
    public String getDiseases(Model model){
        Map<Department, Set<Disease>> diseasesByDepartment = new HashMap<>();

        List<Department> departments = departmentService.findAll();
        Set<Disease> tempDiseases;

        for (Department department : departments){
            tempDiseases = diseaseService.findByDepartment(department);
            diseasesByDepartment.put(department, tempDiseases);
            tempDiseases = null;
        }

        model.addAttribute("diseasesByDepartment", diseasesByDepartment);

        doctorService.addSearchOptions(model);
        return "diseases";
    }
}
