package com.example.quiz.controller;

import com.example.quiz.entity.Quiz;
import com.example.quiz.form.QuizForm;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    private QuizService service;

    @ModelAttribute
    public QuizForm setUpForm(){
        QuizForm form = new QuizForm();
        form.setNewQuiz(true);
        return form;
    }

    @GetMapping
    public String showList(QuizForm quizForm, Model model){
        quizForm.setNewQuiz(true);
        quizForm.setAnswer(true);

        Iterable<Quiz> list = service.selectAll();

        model.addAttribute("list", list);
        model.addAttribute("title", "등록 폼");
        return "crud";
    }

    @PostMapping("insert")
    public String insert(
            @Validated QuizForm quizForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        if(bindingResult.hasErrors()){
            return showList(quizForm, model);
        }
        else{
            Quiz quiz = makeQuiz(quizForm);

            service.insertQuiz(quiz);

            redirectAttributes.addAttribute("complete", "등록이 완료되었습니다.");

            return "redirect:/quiz";
        }
    }

    @PostMapping("update")
    public String update(
            @Validated QuizForm quizForm,
            BindingResult bindingResult,
            Model model
    ){
        return "";
    }

    public Quiz makeQuiz(QuizForm quizForm){
        Quiz quiz = new Quiz();
        quiz.setQuestion(quizForm.getQuestion());
        quiz.setAnswer(quizForm.getAnswer());
        quiz.setAuthor(quizForm.getAuthor());
        return quiz;
    }


}
