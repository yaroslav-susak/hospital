package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.service.AdminService;
import com.itcluster.javaadvanced2.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;


    @GetMapping("/find-user")
    public String adminControl(){
        return "admincontrol";
    }

    @GetMapping("/userinfo")
    public String adminpControl(@RequestParam("email") String email, Model model){
        User user = userService.findUserByEmail(email).get();
        model.addAttribute("foundedUser", user);

        return "userinfo";
    }

}
