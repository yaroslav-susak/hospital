package com.itcluster.javaadvanced2.hospital.service;

import com.itcluster.javaadvanced2.hospital.model.Question;
import com.itcluster.javaadvanced2.hospital.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> findAll(){return questionRepository.findAll();}

    public void delete(Long id){
        Optional<Question> toDelete = questionRepository.findById(id);
        if(toDelete.isPresent()){
            questionRepository.delete(toDelete.get());
        }
    }

    public Question save(Question question){ return questionRepository.save(question);}

}
