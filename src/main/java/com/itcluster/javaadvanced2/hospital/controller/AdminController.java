package com.itcluster.javaadvanced2.hospital.controller;

import com.itcluster.javaadvanced2.hospital.dto.RoleToChangeDTO;
import com.itcluster.javaadvanced2.hospital.dto.UserDoctorDTO;
import com.itcluster.javaadvanced2.hospital.exceptions.BannedUserException;
import com.itcluster.javaadvanced2.hospital.model.Department;

import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.model.Role;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.service.*;
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
    DepartmentService departmentService;

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

    @GetMapping("create-doctor")
    public String createDoctor(Model model){

        model.addAttribute("dto",new UserDoctorDTO());
        model.addAttribute("departments",departmentService.findAll());
        return "create-doctor";
    }

    @GetMapping("/change-doctor")
    public String changeDoctor(Model model,@RequestParam(name="id") Long id){
        Doctor doctor = doctorService.findById(id);
        model.addAttribute("doctor",doctor);
        model.addAttribute("departments", departmentService.findAll());
        return "change-doctor";
    }

    @PostMapping("/save-changed-doctor")
    public String saveChangedDoctor(Doctor doctor) {
        doctorService.save(doctor);
        return "redirect:/admin/find-user";
    }

    @PostMapping("/save-new-doctor-user")
    public String saveNewDoctor(UserDoctorDTO dto){
        User user = new User();
        user.setId(new Long(userService.getLastId()+1));
        user.setFirstName(dto.getName());
        user.setLastName(dto.getSurname());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setPhoto(dto.getPhoto());

        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSurname(dto.getSurname());
        doctor.setMiddleName(dto.getMiddleName());
        doctor.setPhotoName(dto.getPhoto());
        doctor.setQualificationLevel(dto.getQualification());
        doctor.setSpecialization(dto.getSpecialization());

        Department department = new Department();
        department.setId((long)dto.getDepartmentId());
        doctor.setDepartment(department);

        User user2 = userService.createUpdate(user);
        doctor.setUser(user2);

        doctorService.save(doctor);

        return "redirect:/admin/find-user";
    }

    @GetMapping("/find-user")
    public String findUser(Model model){
        doctorService.addSearchOptions(model);
        List<Department> departments = departmentService.findAll();

        List<Doctor> doctors = doctorService.findAll();

        model.addAttribute("departments",departments);
        model.addAttribute("department", new Department());


        model.addAttribute("doctors",doctors);
        model.addAttribute("doctor", new Doctor());
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

        List<Role> allRoles = roleService.getAll();
        allRoles.removeAll(user.getRoles());

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
