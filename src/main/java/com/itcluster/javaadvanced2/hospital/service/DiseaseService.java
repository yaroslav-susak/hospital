package com.itcluster.javaadvanced2.hospital.service;

import com.itcluster.javaadvanced2.hospital.model.Department;
import com.itcluster.javaadvanced2.hospital.model.Disease;
import com.itcluster.javaadvanced2.hospital.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DiseaseService {

    @Autowired
    DiseaseRepository diseaseRepository;

    public Disease findById(Long id){
        return diseaseRepository.findById(id).orElse(null);
    }

    public Set<Disease> findByDepartment(Department department){
        return diseaseRepository.findByDepartment(department);
    }
}
