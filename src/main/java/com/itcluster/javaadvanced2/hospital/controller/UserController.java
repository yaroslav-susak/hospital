package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.dto.PasswordCheckingDTO;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("basePath")
    private String basePath;

    @GetMapping("/cabinet")
    public String userCabinet() {
        return "cabinet";
    }

    @ModelAttribute("user")
    public User activeUser(Authentication authentication) {
        return userService.findUserByEmail(authentication.getName()).get();
    }

    @PostMapping("/save")
    public String saveUser(User user)
    {
        log.info("new user {}", user);
        userService.createUpdate(user);
        return "cabinet";
    }

    @GetMapping("/passwordchange")
    public String changePassword(@ModelAttribute("user") User user)
    {

        return "passwordchange";
    }

    @PostMapping("/savePassword")
    public String savePassword(@ModelAttribute("user") User user,PasswordCheckingDTO passwordCheckingDTO)
    {
        BCryptPasswordEncoder coder = new BCryptPasswordEncoder();

        if(coder.matches(passwordCheckingDTO.getPasswordOld(),user.getPassword()) &&
                passwordCheckingDTO.getPasswordRawFir().equals(passwordCheckingDTO.getPasswordRawSec())){
            User user1 = user;
            user1.setPassword(coder.encode(passwordCheckingDTO.getPasswordRawFir()));
            userService.createUpdate(user1);
        }else{
            return "passwordchange";
        }
        return "cabinet";
    }

    @PostMapping("/uploadPhoto")
    public String uploadPhoto(@RequestParam("file") MultipartFile file,
                              @ModelAttribute("user") User user
    ) {
        String uploadName = "userPhoto"+user.getId()+".jpg";

        try {
            File transferFile = new File(basePath +"/" + uploadName);
            file.transferTo(transferFile);
            user.setPhoto(uploadName);
            userService.createUpdate(user);
        } catch (IOException e) {
            System.out.println("Error saving file");
        }

        return "redirect:/user/cabinet";
    }
}
