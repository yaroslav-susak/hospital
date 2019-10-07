package com.itcluster.javaadvanced2.hospital.repository;

import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.model.Review;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByDoctor(Doctor doctor);
}
