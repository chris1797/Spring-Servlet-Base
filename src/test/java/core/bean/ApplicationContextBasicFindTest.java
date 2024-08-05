package core.bean;

import core.AppConfig;
import core.member.MemberService;
import core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        // ac.getBean() : 빈 이름으로 빈 객체(인스턴스)를 조회
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로 조회")
    void findBeanByType() {
        // ac.getBean() : 타입으로 빈 객체(인스턴스)를 조회
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("구현체 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 실패 테스트")
    void findFailBeanByName() {
        // NoSuchBeanDefinitionException : 빈 이름이 없으면 예외 발생
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("?????", MemberService.class));
    }
}
