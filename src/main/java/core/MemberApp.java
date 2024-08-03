package core;

import core.member.Grade;
import core.member.Member;
import core.member.MemberService;
import core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        /*
         * ApplicationContext는 사실상 스프링 컨테이너라고 볼 수 있고, @Configuration이 붙은 AppConfig를 설정(구성) 정보로 사용한다.
         * ApplicationContext를 사용하면 AppConfig에 있는 @Bean 설정 정보를 가지고 빈(객체)을 관리하고 의존관계를 자동으로 연결해준다.
         *
         * 기존에는 개발자가 직접 의존관계를 연결했지만, 이제는 스프링 컨테이너에 객체를 @Bean으로 등록하고, 스프링 컨테이너에서 이 객체를 찾아서 주입받는다.
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
