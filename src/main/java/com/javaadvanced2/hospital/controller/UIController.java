package com.javaadvanced2.hospital.controller;

import com.javaadvanced2.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("/")
    public String homePage(){
        return "homepage";
    }


}
