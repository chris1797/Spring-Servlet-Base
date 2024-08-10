package core.singleton;

import core.AppConfig;
import core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 스프링은 기본적으로 Bean 을 싱글톤으로 관리하고, 스프링 컨테이너를 생성할 때 Bean 을 미리 다 생성해둔다.
 * 스프링 컨테이너는 싱글톤 레지스트리이기 때문에 싱글톤 객체를 생성하고 관리하는 기능을 가지고 있다.
 * 해당 테스트 케이스는 순수한 DI 컨테이너인 AppConfig와 싱글톤 패턴을 적용한 SingletonService를 비교하기 위한 테스트 케이스이다.
 *
 * 스프링은 기본적으로 Bean을 싱글톤으로 관리하지만 싱글톤 방식만 지원하는 것은 아니고,
 * 요청할 때 마다 새로운 객체를 생성해서 반환하는 프로토타입 빈기능도 제공한다.
 */
public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {

        // new SingletonService(); : private 생성자이기 때문에 컴파일 오류 발생

        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // isSameAs, same : ==
        // isEqualsTo, equals : equals()
        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        // AppConfig appConfig = new AppConfig(); 를 스프링 컨테이너로 변경
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 같은 것 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
