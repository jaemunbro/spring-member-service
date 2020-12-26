package com.example.springstudy.service;

import com.example.springstudy.domain.Member;
import com.example.springstudy.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    // 객체가 다른 repo test에서도 생성했는데.. 또 생성할 필오 있을까?
    // repository의 store는 static이라 객체와 관계 없이 클래스에 가서 붙으므로 상관 없긴 한데..
    // static이 아닌 경우 바로 다른 DB나 다름 없게 됨.
    // 그래서 멤버서비스로 가서 생성자를 만들고 DI를 해보자.

    //MemberService memberService = new MemberService();
    MemberService memberService;
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        // dependency injection  (DI)
        // member를 외부에서 넣어줌 .memberService 입장에서 보면
        memberRepository =  new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    // 테스트는 예외 flow가 더 중요해. 예외 터지는걸 봐야한다.
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        // try/catch 대신 assertThrows를 쓸 수 있다.
        // 뒤의 로직 실행하면, 앞의 예외가 터져야돼
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 메세지 받아서 검증할려면
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // 위의 테스트 실패 시키려면 null pointer exception을 내보자.
        // assertThrows(NullPointerException.class, () -> memberService.join(member2));


        /*
        try {
            memberService.join(member2);
            fail("에외 발생해야함");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미존재")
        }
        */
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}