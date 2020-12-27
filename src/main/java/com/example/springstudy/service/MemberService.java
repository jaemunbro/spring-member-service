package com.example.springstudy.service;

import com.example.springstudy.domain.Member;
import com.example.springstudy.repository.MemberRepository;
import com.example.springstudy.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    // 서비스 위해 먼저 레포지토리 있어야겟지
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        /*
        // 비즈니스 정의에 같은 이름이 있음 안된다.
        // result 받아오도록 하는 단축키는 커맨드 옵션 + v
        Optional<Member> result = memberRepository.findByName(member.getName());
        // option이라는 거를 감싸면
        // 예전에는 if null로 코딩하고 했지만 option으로 감싼 덕분에.. optional의 여러 메소드를 쓸 수 있다.
        // get으로 바로 떠내는 걸 권장하진 않는다. orElseGet 으로도 마니 쓴다.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member); // 증복회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }

    }

    private void validateDuplicateMember(Member member) {
        // optional을 바로 반환하는게 별로 안좋아. 일단 이쁘지도 않아.
        // findByname의 결과는 optional member니까 바로 받아서 처리하도록 함.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        // ctrl + t로 메소드로 뽑자
    }


    // 서비스는 business 의존적이고, repository는 개발쪽에 맞게
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
