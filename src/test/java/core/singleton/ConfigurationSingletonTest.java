package core.singleton;

import core.AppConfig;
import core.member.MemberRepository;
import core.member.MemberService;
import core.member.MemberServiceImpl;
import core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);


        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        // 각자의 Bean에서 가져온 memberRepository는 모두 같은 인스턴스인가?
        // AppConfig에서 각각의 메서드를 호출할 때 이미 싱글톤이 보장되어서 같은 인스턴스가 반환된다.
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        // memberRepository1 == memberRepository2 == memberRepository
        assertThat(memberRepository1).isSameAs(memberRepository);
        assertThat(memberRepository2).isSameAs(memberRepository);

    }
}
