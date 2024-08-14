package core.order;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/*
 AppConfig에서 @Configuration을 제거하고 AutoAppConfig를 만들어서 ComponentScan을 사용하면 AppConfig에 있는 @Bean들을 스캔해서 스프링 빈으로 등록할 수 있다.
 @ComponentScan은 @Component가 붙은 모든 클래스를 스프링 빈으로 등록한다.
 */
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Configuration.class)
)
public class AutoAppConfig {


}
