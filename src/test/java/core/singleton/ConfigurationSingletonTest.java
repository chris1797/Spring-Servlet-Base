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

    @Test
    void configurationDeep2() {
        // AnnotationConfigApplicationContext의 생성자 파라미터로 AppConfig.class를 넘기면 AppConfig에 있는 @Bean들을 스프링 빈으로 등록한다.
        // 스프링 빈으로 등록된 AppConfig를 getBean으로 가져올 수 있다.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        // AppConfig는 Configuration이므로 스프링 컨테이너에 등록되어있는가?
        System.out.println("bean = " + bean);

        /*
        CGLIB는 스프링이 내부적으로 사용하는 바이트코드 조작 라이브러리이다.
        이 라이브러리를 사용하면 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록할 수 있다.
        AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다.
        그리고 이 임의의 다른 클래스가 싱글톤이 보장되도록 해준다.
         */


    }
}
