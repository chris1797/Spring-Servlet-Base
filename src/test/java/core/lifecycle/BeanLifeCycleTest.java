package core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    void lifeCycleTest() {

        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        // 스프링 컨테이너 생성
        NetworkClient client = ac.getBean(NetworkClient.class);

        // 스프링 컨테이너 종료
        ac.close();
    }

    @Configuration
    static class TestConfig {

        /*
        라이브러리는 대부분이 close, shutdown, destroy 메서드를 제공한다.
        destroyMethod는 기본값이 (inferred)로 되어 있는데 close, shutdown, destroy 메서드를 자동으로 호출해주기 때문에 별도로 설정하지 않아도 된다.
        disconnect 메서드를 공백으로 설정하면 close, shutdown, destroy 메서드를 자동으로 호출해주지 않는다.
         */
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

    }

}
