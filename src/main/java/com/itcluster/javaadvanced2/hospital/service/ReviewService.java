package com.itcluster.javaadvanced2.hospital.service;

import com.itcluster.javaadvanced2.hospital.model.Doctor;
import com.itcluster.javaadvanced2.hospital.model.Review;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Slf4j
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

    public Boolean userAlreadyLeftReview(List<Review> reviews, User user) {
        if (!reviews.isEmpty()) {
            for (Review review : reviews) {
                if (review.getPatient().getEmail().equals(user.getEmail())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void userReviewFirst(List<Review> reviews, User user){
        Review userReview = null;

        for (Iterator<Review> it = reviews.iterator(); it.hasNext(); ){
            Review review = it.next();
            if (review.getPatient().getEmail().equals(user.getEmail())){
                userReview = review;
                it.remove();
            }
        }

        if (userReview != null) {
            reviews.add(0, userReview);
        }
    }

    public void delete(Review review){
        reviewRepository.delete(review);
    }

    public Review findById(Long id){
        return reviewRepository.findById(id).orElse(null);
    }
}
