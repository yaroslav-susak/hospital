package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.dto.RoleToChangeDTO;
import com.itcluster.javaadvanced2.hospital.model.Department;
import com.itcluster.javaadvanced2.hospital.model.Role;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    RoleService roleService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    DepartmentService departmentService;

    @ModelAttribute("user")
    public User activeUser(Authentication authentication) {
        return userService.findUserByEmail(authentication.getName()).get();
    }

    @GetMapping("/change-department")
    public String changeDepartment(Model model,@RequestParam(name="id") Long id){
        Department department = departmentService.findById(id);
        model.addAttribute("department",department);
        return "change-department";
    }

    @PostMapping("/save-changed-department")
    public String saveChangedDepartment(Department department){
        departmentService.save(department);
        return "redirect:/admin/find-user";
    }

    @PostMapping("/delete-department")
    public String deleteDepartment(@RequestParam(name="id") Long id){
        departmentService.delete(departmentService.findById(id));
        return "redirect:/admin/find-user";
    }

    @GetMapping("/find-user")
    public String findUser(Model model){
        doctorService.addSearchOptions(model);
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments",departments);
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
        model.addAttribute("roleToDeleteDTO", new RoleToChangeDTO() );
        model.addAttribute("rolesToAdd", roleService.getAll());
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

    @PostMapping("/user-info")
    public String updateUserRole(@ModelAttribute("roleToChangeDTO") RoleToChangeDTO roleToChangeDTO, Model model){

        User user = userService.findUserByEmail(roleToChangeDTO.getEmail()).get();

        Set<Role> roles = user.getRoles();
        roles.add(roleService.getByName(roleToChangeDTO.getRole()));
        user.setRoles(roles);

        userService.createUpdate(user);

        return "redirect:/admin/user-info?userId=" + user.getId();
    }
}
