package com.example.quiz.controller;

import com.example.quiz.entity.Quiz;
import com.example.quiz.form.QuizForm;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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

    @PostMapping("/insert")
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
            Quiz quiz = new Quiz();
            quiz.setQuestion(quizForm.getQuestion());
            quiz.setAnswer(quizForm.getAnswer());
            quiz.setAuthor(quizForm.getAuthor());

            service.insertQuiz(quiz);

            redirectAttributes.addFlashAttribute("complete", "등록이 완료되었습니다.");
            return "redirect:/quiz";
        }
    }

    @GetMapping("/{id}")
    public String showUpdate(
            @PathVariable Integer id,
            QuizForm quizForm,
            RedirectAttributes redirectAttributes,
            Model model
    ){
        Optional<Quiz> optQuiz = service.selectOneById(id);
        if(optQuiz.isPresent()) {
            Quiz quiz = optQuiz.get();
            quizForm = makeQuizForm(quiz);
            model.addAttribute("quizForm", quizForm);
            model.addAttribute("title", "변경폼");
            return "crud";
        }
        redirectAttributes.addFlashAttribute("complete", "존재하지 않는 퀴즈입니다.");
        return "redirect:/quiz";
    }

    @PostMapping("/update")
    public String update(
            @Validated QuizForm quizForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        if(!bindingResult.hasErrors()){
            Quiz quiz = makeQuiz(quizForm);
//            System.out.println("id = " + quiz.getId());
            service.updateQuiz(quiz);
            redirectAttributes.addFlashAttribute("complete", "변경이 완료되었습니다.");
            return "redirect:/quiz/" + quizForm.getId();
        }
        quizForm.setNewQuiz(false); // form에 newQuiz값이 없으니 다시 할당
        //model.addAttribute("quizForm", quizForm);
        //model.addAttribute("title", "변경폼");
        return "crud";
    }

    @PostMapping("/delete")
    public String delete(
            @RequestParam("id") String id,
           // Model model,
            RedirectAttributes redirectAttributes
    ){
        service.deleteQuizById(Integer.parseInt(id));
        redirectAttributes.addFlashAttribute("delComplete", "삭제가 완료되었습니다.");
        return "redirect:/quiz";
    }

    @GetMapping("/play")
    public String play(Model model){
        Optional<Quiz> optQuiz = service.selectOneRandomQuiz();
        if(optQuiz.isPresent()){
            Quiz quiz = optQuiz.get();
            model.addAttribute("id", quiz.getId());
            model.addAttribute("question", quiz.getQuestion());
        }
        else{
            model.addAttribute("msg", "등록된 퀴즈가 없습니다.");
        }
        return "play";
    }

    @PostMapping("/check")
    public String checkAnswer(
            @RequestParam("id") String id,
            @RequestParam("answer") Boolean answer,
            Model model
    ){
        boolean result = service.checkQuiz(Integer.parseInt(id), answer);
        // 주석 처리된 아래 코드를 사용하면, 왜 정답을 제대로 내놓지 못할까??
//        model.addAttribute("answer", answer);
//        model.addAttribute("result", result);
        if(result){
            model.addAttribute("msg", "정답입니다.");
        }
        else{
            model.addAttribute("msg", "오답입니다.");
        }
        return "answer";
    }

    public Quiz makeQuiz(QuizForm quizForm){
        Quiz quiz = new Quiz();
        quiz.setId(quizForm.getId());
        quiz.setQuestion(quizForm.getQuestion());
        quiz.setAnswer(quizForm.getAnswer());
        quiz.setAuthor(quizForm.getAuthor());
        return quiz;
    }

    public QuizForm makeQuizForm(Quiz quiz){
        QuizForm form = new QuizForm();
        form.setId(quiz.getId());
        form.setQuestion(quiz.getQuestion());
        form.setAnswer(quiz.getAnswer());
        form.setAuthor(quiz.getAuthor());
        form.setNewQuiz(false);
        return form;
    }
}
