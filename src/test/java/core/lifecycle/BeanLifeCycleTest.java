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

        NetworkClient client = ac.getBean(NetworkClient.class);
        client.setUrl("http://hello-spring.dev");

        client.connect();
        client.call("hello");
        client.disconnect();
    }

    @Configuration
    static class TestConfig {
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

    }

}
