package com.itcluster.javaadvanced2.hospital.service;

import com.itcluster.javaadvanced2.hospital.dto.CommentDTO;
import com.itcluster.javaadvanced2.hospital.model.Comment;
import com.itcluster.javaadvanced2.hospital.model.News;
import com.itcluster.javaadvanced2.hospital.model.User;
import com.itcluster.javaadvanced2.hospital.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    CommentService commentService;

    public News findById(Long id){
        return newsRepository.findById(id).orElse(null);
    }

    public List<News> findByAuthor(User author){
        return newsRepository.findByAuthor(author);
    }

    public List<News> findAllNewsByAuthor(User author){
        List<News> news = new ArrayList<>();

        for (News newsItem : findByAuthor(author)){
            if(newsItem.getType().equals("NEWS")){
                news.add(newsItem);
            }
        }

        return news;
    }

    public List<News> findArticlesByAuthor(User author){
        return filterAuthorNewsByType(author, "ARTICLE");
    }

    public List<News> findNewsByAuthor(User author){
        return filterAuthorNewsByType(author, "NEWS");
    }

    public List<News> filterAuthorNewsByType(User author, String type){
        List<News> news = new ArrayList<>();

        for (News newsItem : findByAuthor(author)){
            if(newsItem.getType().equals(type)){
                news.add(newsItem);
            }
        }

        return news;
    }
    public List<News> findByType(String type){
        return newsRepository.findByType(type);
    }

    public void formNewsOrArticle(Long id, User user, Model model){
        News news = findById(id);
        List<Comment> comments = commentService.findByNews(news);
        Collections.sort(comments);

        if(user != null) {
            commentService.userCommentsFirst(comments, user);
        }

        String newsText = news.getText();
        String[] newsTextParts = newsText.split("\\r?\\n");
        model.addAttribute("news", news);
        model.addAttribute("newsText", newsTextParts);
        model.addAttribute("comments", comments);
        model.addAttribute("commentDTO", new CommentDTO());
    }

    public void save(News news){
        newsRepository.save(news);
    }
}
