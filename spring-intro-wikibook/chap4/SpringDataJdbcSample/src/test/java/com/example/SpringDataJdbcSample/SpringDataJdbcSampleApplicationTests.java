package com.example.SpringDataJdbcSample;

import com.example.SpringDataJdbcSample.entity.Member;
import com.example.SpringDataJdbcSample.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDataJdbcSampleApplicationTests {

	@Autowired
	MemberService service;

	void contextLoads() {
	}

	@Test
	public void executeInsert(){
		Member member = new Member(null, "이순신");
		member = service.save(member);
		System.out.println("등록 데이터 : " + member);
	}

	@Test
	public void executeSelect(){
		System.out.println("전체 데이터를 취득");
		Iterable<Member> members = service.findAll();
		for(Member member : members){
			System.out.println(member);
		}
	}

}
