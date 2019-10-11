package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.dto.CommentDTO;
import com.itcluster.javaadvanced2.hospital.exceptions.BannedUserException;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.service.NewsService;
import com.itcluster.javaadvanced2.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User activeUser(Authentication authentication) {
        if (authentication != null) {
            User user = userService.findUserByEmail(authentication.getName()).get();
            if (user.isBanned()) {
                throw new BannedUserException();
            } else {
                return user;
            }
        }
        return  null;
    }
}
