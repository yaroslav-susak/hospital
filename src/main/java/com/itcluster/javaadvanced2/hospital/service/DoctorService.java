package com.itcluster.javaadvanced2.hospital.service;

import com.itcluster.javaadvanced2.hospital.exceptions.DoctorNotFoundException;
import com.itcluster.javaadvanced2.hospital.model.Department;
import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> {
                DoctorNotFoundException e = new DoctorNotFoundException(id);
                log.error("Doctor not found",e);
                return e;
        });
    }

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }

    public HashSet<Doctor> findByDepartment(Department department){
        return doctorRepository.findByDepartment(department);
    }


    public HashSet<Doctor> findByQualificationLevel(String qualificationLevel){
        return doctorRepository.findByQualificationLevel(qualificationLevel);
    }

//    public void removeDifferent(HashSet<Doctor> from, HashSet<Doctor> arg){
//        Set<Doctor> tmp = new HashSet<>(from);
//        tmp.removeAll(arg);
//        from.removeAll(tmp);
//    }

    public HashSet<Doctor> findBySurname(String surname){
        return doctorRepository.findBySurname(surname);
    }

    public void delete(Long id){
        Optional<Doctor> toDelete = doctorRepository.findById(id);
        if (toDelete.isPresent()) {
            doctorRepository.delete(toDelete.get());
        }
    }

    public Doctor save(Doctor doctor){
        return doctorRepository.save(doctor);
    }
}
