package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class LoginController {

    private static final String EMPTY_FIELD_MESSAGE = "Поле має бути заповненим";
    private static final String EMAIL_ALREADY_USED_MESSAGE ="Користувач з таким e-mail вже існує";
    private static final String SUCCESSFULLY_REGISTERED_MESSAGE = "Користувача зареєстровано";

    @Autowired
    private UserService userService;

    @GetMapping(value={"/login"})
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String createNewUser(@Valid User user, Model model) {
        Optional<User> userExists = userService.findUserByEmail(user.getEmail());
        if (userExists.isPresent()) {
            model.addAttribute("usedEmail", EMAIL_ALREADY_USED_MESSAGE);
            return "registration";
        }
        user = userService.createUpdate(user);
        model.addAttribute("successMessage", SUCCESSFULLY_REGISTERED_MESSAGE);
        model.addAttribute("user", user);
        return "registration";
    }

    @ModelAttribute("user")
    public User activeUser(Authentication authentication) {
        return userService.findUserByEmail(authentication.getName()).get();
    }

    @GetMapping("/cabinet")
    public String userProfile() {
        return "cabinet";
    }

    @PostMapping("uploadPhoto")
    public String uploadPhoto(@RequestParam("file") MultipartFile file,
                              @ModelAttribute("user") User user
    ) {
        String uploadName = file.getOriginalFilename();

        try {
            File transferFile = new File("/" + uploadName);
            file.transferTo(transferFile);
            user.setPhoto(uploadName);
            userService.createUpdate(user);
        } catch (IOException e) {
            System.out.println("Error saving file");
        }

        return "redirect:/user/cabinet";
    }
}
