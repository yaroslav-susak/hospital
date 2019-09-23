package com.itcluster.javaadvanced2.hospital.repository;

import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.model.Role;
import com.itcluster.javaadvanced2.hospital.model.Schedule;
import com.itcluster.javaadvanced2.hospital.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByDoctor(Doctor doctor);
    List<Schedule> findByPatient(User user);
}