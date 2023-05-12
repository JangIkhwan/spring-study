package com.example.SpringDataJdbcSample;

import com.example.SpringDataJdbcSample.entity.Member;
import com.example.SpringDataJdbcSample.repository.MemberCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJdbcSampleApplication {
	@Autowired
	MemberCrudRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJdbcSampleApplication.class, args)
				.getBean(SpringDataJdbcSampleApplication.class).execute();
	}

	public void execute(){
		executeInsert();
		executeSelect();
	}
	public void executeInsert(){
		Member member = new Member(null, "이순신");
		member = repository.save(member);
		System.out.println("등록 데이터 : " + member);
	}

	public void executeSelect(){
		System.out.println("전체 데이터를 취득");
		Iterable<Member> members = repository.findAll();
		for(Member member : members){
			System.out.println(member);
		}
	}

}
