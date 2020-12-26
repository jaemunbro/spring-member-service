package com.example.springstudy;

import com.example.springstudy.repository.JpaMemberRepository;
import com.example.springstudy.repository.MemberRepository;
import com.example.springstudy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContexts;

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    // spring 뜨면서 스프링 빈에 등록해준다.
    // bean을 등록할거야!
    @Bean
    public MemberService memberService() {
        // command + P로 확인해보면 뭘 넣어야도는지 나온다
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JDBCTemplateMemberRepository(dataSource);
        return new JpaMemberRepository();
    }

}
