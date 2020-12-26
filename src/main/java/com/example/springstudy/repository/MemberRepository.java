package com.example.springstudy.repository;

import com.example.springstudy.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    //null이 발생될 수 있는 경우 optional로 싼다. 선호하는 방식
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
