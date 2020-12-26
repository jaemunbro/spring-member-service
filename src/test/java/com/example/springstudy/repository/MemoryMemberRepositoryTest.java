package com.example.springstudy.repository;

import com.example.springstudy.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 갖다 쓸 꺼 아니니까 public class 필요 없다.
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test 끝날때마다 수행
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // annotation만 적어주면 junit import 하고 실행할 수 있다.
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        // 저장하고
        repository.save(member);
        // 바로 확인
        Member result = repository.findById(member.getId()).get();

        //출력해보는 방법도 있는데
        System.out.println("result = " + (result == member));
        // 문자로 계속 볼 수 없으니까 assertions
        // Assertions.assertEquals(member, result);
        //assertj가 좀더 편해서 요거 쓴다 요즘에는
        assertThat(member).isEqualTo(result);
        // static import : option enter 해서 static import 해놓고 바로 assert That 쓸 수 있다.

        // 실무에서는 빌드 툴에 엮어서 빌드에서 오류 테스트를 통과하지 못하면 다음 단계로 못넘어가게 막는다.

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        //assertThat(result).isEqualTo(member1);
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}
