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
    public Doctor findById(@PathVariable Long id){
        return doctorService.findById(id);
    }

    @GetMapping("department/{department}")
    public List<Doctor> getByDepartment(@PathVariable String department){
        return doctorService.findByDepartment(department);
    }

    @PostMapping
    public Doctor save(@RequestBody Doctor doctor){
        return doctorService.save(doctor);
    }

    @GetMapping("qualification/{qualificationLevel}")
    public List<Doctor> findByQualificationLevel(@PathVariable String qualificationLevel){
        return doctorService.findByQualificationLevel(qualificationLevel);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        doctorService.delete(id);
    }

    @GetMapping("all")
    public List<Doctor> findAll(){
        return doctorService.findAll();
    }
    }