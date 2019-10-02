package com.itcluster.javaadvanced2.hospital.repository;

import com.itcluster.javaadvanced2.hospital.model.Department;
import com.itcluster.javaadvanced2.hospital.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    Set<Disease> findByDepartment(Department department);
}
