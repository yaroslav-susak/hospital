package com.itcluster.javaadvanced2.hospital.service;

import com.itcluster.javaadvanced2.hospital.model.News;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public News findById(Long id){
        return newsRepository.findById(id).orElse(null);
    }

    public List<News> findByAuthor(User author){
        return newsRepository.findByAuthor(author);
    }

    public List<News> findByType(String type){
        return newsRepository.findByType(type);
    }
}
