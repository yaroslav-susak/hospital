package com.itcluster.javaadvanced2.hospital.service;

import com.itcluster.javaadvanced2.hospital.model.Role;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.repository.RoleRepository;
import com.itcluster.javaadvanced2.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    public void setUserRole(User user, String roleName){
        userService.addUserRole(user, roleRepository.findByName(roleName));
    }

    public void deleteUserRole(User user, String roleName){
        userService.deleteUserRole(user, roleRepository.findByName(roleName));
    }

}
