package com.example.SpringDataJdbcSample.service;

import com.example.SpringDataJdbcSample.entity.Member;
import com.example.SpringDataJdbcSample.repository.MemberCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberCrudRepository repository;

    @Autowired
    public MemberService(MemberCrudRepository repository) {
        this.repository = repository;
    }

    public Member save(Member member){
        return repository.save(member);
    }

    public Iterable<Member> findAll(){
        return repository.findAll();
    }


}
