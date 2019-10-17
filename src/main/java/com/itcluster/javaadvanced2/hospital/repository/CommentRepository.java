package com.itcluster.javaadvanced2.hospital.repository;

import com.itcluster.javaadvanced2.hospital.model.Comment;
import com.itcluster.javaadvanced2.hospital.model.News;
import com.itcluster.javaadvanced2.hospital.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByNews(News news);
    List<Comment> findByUser(User user);
}
