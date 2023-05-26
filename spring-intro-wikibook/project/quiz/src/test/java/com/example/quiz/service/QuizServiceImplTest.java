package com.example.quiz.service;

import com.example.quiz.entity.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class QuizServiceImplTest {
    @Autowired
    QuizServiceImpl service;

    @BeforeEach
    void 사전데이터등록(){
        Quiz quiz1 = new Quiz(null, "자바는 객체지향언어이다.", true, "등록 담당");
        Quiz quiz2 = new Quiz(null, "스프링 데이터는 데이터 엑세스에 관련된 기능을 제공한다.",true, "등록 담당");
        Quiz quiz3 = new Quiz(null, "프로그램이 많이 등록되어 있는 서버를 프레임워크라고 한다.", false, "등록 담당");
        Quiz quiz4 = new Quiz(null, "@Component는 인스턴스 생성 어노테이션이다.", true, "등록 담당");
        Quiz quiz5 = new Quiz(null, "스프링 MVC에서 구현하고 있는 디자인 패턴 중에서 모든 요청을 하나의 컨트롤로에서 받는 것을 싱글 컨트롤러 패턴이라고 한다.", false, "등록 담당");

        List<Quiz> quizList = new ArrayList<>();
        Collections.addAll(quizList, quiz1, quiz2, quiz3, quiz4, quiz5);

        for(Quiz quiz : quizList){
            service.insertQuiz(quiz);
        }
    }

    @Test
    void 모든데이터_취득(){
        System.out.println("---모든 데이터 취득 시작---");
        Iterable<Quiz> quizzes = service.selectAll();
        for(Quiz quiz : quizzes){
            System.out.println(quiz);
        }
        System.out.println("---모든 데이터 취득 완료---");
    }

    @Test
    void 데이터하나_취득(){
        System.out.println("---데이터 하나 취득 시작---");
        Integer id = service.selectOneRandomQuiz().get().getId();
        Optional<Quiz> optQuiz = service.selectOneById(id);
        if(optQuiz.isPresent()){
            System.out.println(optQuiz);
        }
        System.out.println("---데이터 하나 취득 완료---");
    }

    @Test
    void 갱신(){
        // given
        Optional<Quiz> optQuiz = service.selectOneRandomQuiz();
        Quiz quiz = optQuiz.get();
        quiz.setQuestion("HTTP는 stateless 프로토콜입니까?");
        quiz.setAnswer(true);

        //when
        service.updateQuiz(quiz);
        System.out.println("변경된 객체는 " + quiz + "입니다.");

        //then
        모든데이터_취득();
    }

    @Test
    void 랜덤퀴즈선택(){
        Optional<Quiz> quiz = service.selectOneRandomQuiz();
        if(quiz.isPresent()) {
            System.out.println(quiz);
        }
        System.out.println("---랜덤 퀴즈 선택 완료---");
    }
}
