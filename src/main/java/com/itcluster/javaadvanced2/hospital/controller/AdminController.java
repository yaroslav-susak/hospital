package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.service.DoctorService;
import com.itcluster.javaadvanced2.hospital.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    DoctorService doctorService;

    @ModelAttribute("user")
    public User activeUser(Authentication authentication) {
        return userService.findUserByEmail(authentication.getName()).get();
    }

    @GetMapping()
    public String adminPage(Model model, @ModelAttribute User user){
        doctorService.addSearchOptions(model);
        model.addAttribute("email","");
        return "admin";
    }

    @PostMapping("/find-user")
    public String findUser(Model model, @RequestParam String email){
        User user = userService.findUserByEmail(email).orElse(null);
        log.info(user.getId().toString());
        return "homepage";
    }
}
