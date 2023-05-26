package com.example.quiz.repository;

import com.example.quiz.entity.Quiz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class QuizRepositoryTest {
    @Autowired
    QuizRepository repository;
    
    @Test
    void 등록(){
        // given
        Quiz quiz1 = new Quiz(null, "2023년 기준 미국 대통령은 바이든인가?", true, "홍길동");

        // when
        Quiz result1 = repository.save(quiz1);

        // then
        assertThat(result1).isEqualTo(quiz1);
        System.out.println("등록한 퀴즈는 " + result1 + "입니다.");
    }

    @Test
    void 모든데이터_조회(){
        // given
        Quiz quiz1 = new Quiz(null, "2023년 기준 미국 대통령은 바이든인가?", true, "홍길동");
        repository.save(quiz1);
        Quiz quiz2 = new Quiz(null, "스프링은 프레임워크인가?", true, "홍길동");
        repository.save(quiz2);

        // when
        Iterable<Quiz> quizzes = repository.findAll();

        // then
        for(Quiz quiz : quizzes){
            System.out.println(quiz);
        }
        System.out.println("----모든 데이터 얻기 완료-----");
    }

    @Test
    void 조회(){
        // given
        Quiz quiz1 = new Quiz(null, "2023년 기준 미국 대통령은 바이든인가?", true, "홍길동");
        repository.save(quiz1);
        Quiz quiz2 = new Quiz(null, "스프링은 프레임워크인가?", true, "홍길동");
        Quiz q = repository.save(quiz2);

        // when
        Optional<Quiz> result = repository.findById(q.getId());

        //then
        assertThat(result.isPresent()).isEqualTo(true);
        assertThat(result.get()).isEqualTo(quiz2);
    }

    @Test
    void 변경(){
        // given
        Quiz quiz1 = new Quiz(null, "2023년 기준 미국 대통령은 바이든인가?", true, "홍길동");
        repository.save(quiz1);
        Quiz quiz2 = new Quiz(null, "스프링은 프레임워크인가?", true, "홍길동");
        quiz2 = repository.save(quiz2);

        //when
        quiz2.setAuthor("변경 담당");
        Quiz result = repository.save(quiz2);

        //then
        assertThat(result).isEqualTo(quiz2);
        System.out.println("변경된 객체는 " + result + "입니다.");
    }

    @Test
    void 삭제(){
        // given
        Quiz quiz1 = new Quiz(null, "2023년 기준 미국 대통령은 바이든인가?", true, "홍길동");
        repository.save(quiz1);
        Quiz quiz2 = new Quiz(null, "스프링은 프레임워크인가?", true, "홍길동");
        quiz2 = repository.save(quiz2);

        // when
        repository.deleteById(quiz2.getId());

        // then
        System.out.println("-----삭제 완료-----");
        Iterable<Quiz> result = repository.findAll();
        for(Quiz q : result){
            assertThat(q).isNotEqualTo(quiz2);
            System.out.println(q);
        }

    }
}
