package com.example.springstudy.repository;

import com.example.springstudy.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //jpa data에서 공통화 할 수 있는 건 다 공통화 했지만
    // name은 공통화가 안돼서 name만 override
    //비즈니스가 달라서 공통화가 불가능하니까

    //interface name만으로 아래 쿼리를 생성해준다
    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
