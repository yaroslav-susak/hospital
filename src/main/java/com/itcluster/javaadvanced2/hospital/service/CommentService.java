package com.itcluster.javaadvanced2.hospital.service;

import com.itcluster.javaadvanced2.hospital.model.Comment;
import com.itcluster.javaadvanced2.hospital.model.News;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.repository.CommentRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    
    public Comment findById(Long id){
        return commentRepository.findById(id).orElse(null);
    }
    
    public List<Comment> findByNews(News news){
        return commentRepository.findByNews(news);
    }
    
    public List<Comment> findByUser(User user){
        return commentRepository.findByUser(user);
    }
}
