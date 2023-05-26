package com.example.quiz.service;

import com.example.quiz.entity.Quiz;

import java.util.Optional;

public interface QuizService {
    Iterable<Quiz> selectAll();
    Optional<Quiz> selectOneById(Integer id);
    Optional<Quiz> selectOneRandomQuiz();
    boolean checkQuiz(Integer id, Boolean myAnswer);
    void insertQuiz(Quiz quiz);
    void deleteQuizById(Integer id);
    void updateQuiz(Quiz quiz);

}