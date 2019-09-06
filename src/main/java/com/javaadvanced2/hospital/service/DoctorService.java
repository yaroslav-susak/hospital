package com.javaadvanced2.hospital.service;

import com.javaadvanced2.hospital.model.Doctor;
import com.javaadvanced2.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public Doctor getOne(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public List<Doctor> getByDepartment(String department){
        return doctorRepository.findByDepartment(department);
    }

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }

    public Doctor create(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getByQualificationLevel(String qualificationLevel){
        return doctorRepository.findByQualificationLevel(qualificationLevel);
    }

    public void delete(Long id){
        Optional<Doctor> toDelete = doctorRepository.findById(id);
        if (toDelete.isPresent()) {
            doctorRepository.delete(toDelete.get());
        }
    }
}
