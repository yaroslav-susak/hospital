package com.javaadvanced2.hospital.repository;

import com.javaadvanced2.hospital.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByDepartment(String department);
    List<Doctor> findByQualificationLevel(String qualificationLevel);
}
