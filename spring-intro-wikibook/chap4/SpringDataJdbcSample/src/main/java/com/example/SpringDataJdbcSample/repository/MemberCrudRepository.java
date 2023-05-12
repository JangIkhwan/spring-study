package com.example.SpringDataJdbcSample.repository;

import com.example.SpringDataJdbcSample.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberCrudRepository extends CrudRepository<Member, Integer> {
}
