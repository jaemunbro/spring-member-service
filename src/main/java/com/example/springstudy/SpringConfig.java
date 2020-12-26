
import com.example.springstudy.repository.MemberRepository;
import com.example.springstudy.repository.MemoryMemberRepository;
import com.example.springstudy.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // spring 뜨면서 스프링 빈에 등록해준다.
    // bean을 등록할거야!
    @Bean
    public MemberService memberService() {
        // command + P로 확인해보면 뭘 넣어야도는지 나온와
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
