package com.itcluster.javaadvanced2.hospital.service;

import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.model.Review;
import com.itcluster.javaadvanced2.hospital.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findByDoctor(Doctor doctor){
        return reviewRepository.findByDoctor(doctor);
    }

    public Review save(Review review){
        return reviewRepository.save(review);
    }
}
