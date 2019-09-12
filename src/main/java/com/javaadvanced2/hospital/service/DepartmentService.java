package com.javaadvanced2.hospital.service;

import com.javaadvanced2.hospital.model.Department;
import com.javaadvanced2.hospital.model.Doctor;
import com.javaadvanced2.hospital.repository.DepartmentRepository;
import com.javaadvanced2.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Doctor> getAllDoctorsFromDepartment(Department department){
        return doctorRepository.findByDepartment(department);
    }

    public Department findByName(String name){
        return departmentRepository.findByName(name);
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public Department findById(Long id){
        return departmentRepository.findById(id).orElse(null);
    }
}
