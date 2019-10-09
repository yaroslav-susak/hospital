package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.dto.RoleToChangeDTO;
import com.itcluster.javaadvanced2.hospital.exceptions.BannedUserException;
import com.itcluster.javaadvanced2.hospital.model.Role;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.service.AdminService;
import com.itcluster.javaadvanced2.hospital.service.DoctorService;
import com.itcluster.javaadvanced2.hospital.service.RoleService;
import com.itcluster.javaadvanced2.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    private RoleService roleService;

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

    @GetMapping("/find-user")
    public String findUser(Model model){
        doctorService.addSearchOptions(model);
        return "admincontrol";
    }

    @PostMapping("/find-user")
    public String findUser(Model model, @RequestParam(name="email") String email){
        User user = userService.findUserByEmail(email).orElse(null);
        return "redirect:/admin/user-info?userId=" + user.getId();
    }

    @GetMapping("/user-info")
    public String adminControl(@RequestParam(name="userId") Long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("foundedUser", user);
        Role banned = roleService.getByName("BANNED");
        model.addAttribute("banned", banned);

        model.addAttribute("roleToDeleteDTO", new RoleToChangeDTO() );

        List<Role> allRoles = roleService.getAll();
        allRoles.removeAll(user.getRoles());
        allRoles.remove(banned);

        model.addAttribute("rolesToAdd", allRoles);
        doctorService.addSearchOptions(model);
        return "userinfo";
    }

    @DeleteMapping("/user-info")
    public String deleteUserRole(@ModelAttribute("roleToChangeDTO") RoleToChangeDTO roleToChangeDTO, Model model){

        User user = userService.findUserByEmail(roleToChangeDTO.getEmail()).get();

        Set<Role> roles = user.getRoles();
        roles.remove(roleService.getByName(roleToChangeDTO.getRole()));
        user.setRoles(roles);

        userService.createUpdate(user);

        return "redirect:/admin/user-info?userId=" + user.getId();
    }

    @PostMapping("/ban-user")
    public String banUser(@ModelAttribute("roleToChangeDTO") RoleToChangeDTO roleToChangeDTO,
                          @RequestParam(name="status") boolean status){
        User user = userService.findUserByEmail(roleToChangeDTO.getEmail()).get();
        user.setBanned(status);
        userService.createUpdate(user);
        return "redirect:/admin/user-info?userId=" + user.getId();
    }

    @PostMapping("/user-info")
    public String updateUserRole(@ModelAttribute("roleToChangeDTO") RoleToChangeDTO roleToChangeDTO){
        User user = userService.findUserByEmail(roleToChangeDTO.getEmail()).get();
        Set<Role> roles = user.getRoles();
        roles.add(roleService.getByName(roleToChangeDTO.getRole()));
        user.setRoles(roles);
        userService.createUpdate(user);
        return "redirect:/admin/user-info?userId=" + user.getId();
    }
}
