package com.example.quiz.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizForm {
    private Integer id;
    @NotBlank
    private String question;
    private boolean answer;
    @NotBlank
    private String author;
    private boolean newQuiz;
}
