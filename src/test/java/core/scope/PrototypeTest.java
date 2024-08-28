package core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        // when
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        // then
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // spring 컨테이너 종료
        ac.close();
    }


    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        /* 왜 호출이 안될까?
         -> 스프링 컨테이너가 종료될 때 PreDestroy가 호출되지 않는다.
         -> 프로토타입 빈은 스프링 컨테이너가 종료될 때 @PreDestroy가 호출되지 않는다.
         -> 따라서 프로토타입 빈의 경우 @PreDestroy가 호출되지 않으므로 직접 종료 메서드를 호출해야 한다.
        */
        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
