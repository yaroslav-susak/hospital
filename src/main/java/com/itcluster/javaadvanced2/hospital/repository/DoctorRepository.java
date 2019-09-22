package com.itcluster.javaadvanced2.hospital.repository;

import com.itcluster.javaadvanced2.hospital.model.Department;
import com.itcluster.javaadvanced2.hospital.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    HashSet<Doctor> findByDepartment(Department department);
    HashSet<Doctor> findByQualificationLevel(String qualificationLevel);
    HashSet<Doctor> findBySurname(String surname);
}
