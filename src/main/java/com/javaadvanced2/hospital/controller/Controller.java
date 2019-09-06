package com.javaadvanced2.hospital.controller;

import com.javaadvanced2.hospital.model.Doctor;
import com.javaadvanced2.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctor")
public class Controller {
    @Autowired
    DoctorService doctorService;

    @GetMapping("{id}")
    public Doctor getOne(@PathVariable Long id){
        return doctorService.getOne(id);
    }

    @GetMapping("department/{department}")
    public List<Doctor> getByDepartment(@PathVariable String department){
        return doctorService.getByDepartment(department);
    }

    @PostMapping
    public Doctor save(@RequestBody Doctor doctor){
        return doctorService.create(doctor);
    }

    @GetMapping("qualification/{qualificationLevel}")
    public List<Doctor> getByQualificationLevel(@PathVariable String qualificationLevel){
        return doctorService.getByQualificationLevel(qualificationLevel);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        doctorService.delete(id);
    }

    @GetMapping("all")
    public List<Doctor> getAll(){
        return doctorService.findAll();
    }
    }