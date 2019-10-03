package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.dto.RoleToChangeDTO;
import com.itcluster.javaadvanced2.hospital.model.Role;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.service.AdminService;
import com.itcluster.javaadvanced2.hospital.service.RoleService;
import com.itcluster.javaadvanced2.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    RoleService roleService;



    @GetMapping("/find-user")
    public String findUser(){
        return "admincontrol";
    }

    @GetMapping("/user-info")
    public String adminControl(@RequestParam("email") String email, Model model){
        User user = userService.findUserByEmail(email).get();
        model.addAttribute("foundedUser", user);
        model.addAttribute("roleToDeleteDTO", new RoleToChangeDTO() );
        model.addAttribute("rolesToAdd", roleService.getAll().removeAll(user.getRoles()));
        return "userinfo";
    }

    @DeleteMapping("/user-info")
    public String deleteUserRole(@ModelAttribute("roleToChangeDTO") RoleToChangeDTO roleToChangeDTO, Model model){

        User user = userService.findUserByEmail(roleToChangeDTO.getEmail()).get();
        model.addAttribute("foundedUser", user);

        Set<Role> roles = user.getRoles();
        roles.remove(roleService.getByName(roleToChangeDTO.getRole()));
        user.setRoles(roles);

        userService.createUpdate(user);

        return "userinfo";
    }

    @PostMapping("/user-info")
    public String updateUserRole(@ModelAttribute("roleToChangeDTO") RoleToChangeDTO roleToChangeDTO, Model model){

        User user = userService.findUserByEmail(roleToChangeDTO.getEmail()).get();
        model.addAttribute("foundedUser", user);

        Set<Role> roles = user.getRoles();
        roles.add(roleService.getByName(roleToChangeDTO.getRole()));
        user.setRoles(roles);

        userService.createUpdate(user);

        return "userinfo";
    }



}
