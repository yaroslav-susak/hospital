package com.itcluster.javaadvanced2.hospital.service;

import com.itcluster.javaadvanced2.hospital.model.Role;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.repository.RoleRepository;
import com.itcluster.javaadvanced2.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUpdate(User user) {
        User toSave = user.getId() == null ? createUser(user) : updateUser(user);
        return userRepository.save(toSave);
    }

    private User createUser(User user) {

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("user"));
        roles.add(roleRepository.findByName("patient"));
        user.setRoles(roles);
        user.setDateOfRegistration(new Date());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return user;

    }

    private User updateUser(User user) {
        User origin = userRepository.findById(user.getId()).get();
        if (!StringUtils.isEmpty(user.getFirstName())) {
            origin.setFirstName(user.getFirstName());
        }
        if (!StringUtils.isEmpty(user.getLastName())) {
            origin.setLastName(user.getLastName());
        }
        if (!StringUtils.isEmpty(user.getEmail())) {
            origin.setEmail(user.getEmail());
        }
        if (!StringUtils.isEmpty(user.getPhoto())) {
            origin.setPhoto(user.getPhoto());
        }
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            origin.setRoles(user.getRoles());
        }
        return origin;
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }
}
