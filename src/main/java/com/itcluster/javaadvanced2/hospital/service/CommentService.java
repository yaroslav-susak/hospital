package com.itcluster.javaadvanced2.hospital.service;

import com.itcluster.javaadvanced2.hospital.model.Comment;
import com.itcluster.javaadvanced2.hospital.model.News;
import com.itcluster.javaadvanced2.hospital.model.Review;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.repository.CommentRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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

    public void save(Comment comment){
        commentRepository.save(comment);
    }

    public void userCommentsFirst(List<Comment> comments, User user){
        List<Comment> userComments = new ArrayList<>();

        for (Iterator<Comment> it = comments.iterator(); it.hasNext(); ){
            Comment comment = it.next();
            if (comment.getUser().getEmail().equals(user.getEmail())){
                userComments.add(comment);
                it.remove();
            }
        }

        if (!userComments.isEmpty()) {
            Collections.sort(userComments);
            comments.addAll(0,userComments);
        }
    }

    public void delete(Comment comment){
        commentRepository.delete(comment);
    }
}
