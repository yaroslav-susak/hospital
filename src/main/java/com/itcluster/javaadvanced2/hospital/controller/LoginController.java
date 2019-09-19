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

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LoginController {

    private static final String EMPTY_FIELD_MESSAGE = "Поле має бути заповненим";
    private static final String EMAIL_ALREADY_USED_MESSAGE ="Користувач з таким e-mail вже існує";
    private static final String SUCCESSFULLY_REGISTERED_MESSAGE = "Користувача зареєстровано";

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User activeUser(Authentication authentication) {
        return new User();
    }

    @GetMapping(value={"/login"})
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String createNewUser(@Valid User user,
                                BindingResult bindingResult,
                                Model model
    ) {
        if (bindingResult.hasErrors() || userExists(user, bindingResult))  {
            return "registration";
        }

        user = userService.createUpdate(user);
        return "homepage";
    }

    private boolean userExists(User user, BindingResult bindingResult) {
        boolean result = userService.findUserByEmail(user.getEmail()).isPresent();
        if (result) {
            bindingResult.rejectValue("email", "error.user", EMAIL_ALREADY_USED_MESSAGE);
        }
        return result;
    }
}
