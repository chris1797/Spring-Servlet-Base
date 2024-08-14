package core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/*
 AppConfig에서 @Configuration을 제거하고 AutoAppConfig를 만들어서 ComponentScan을 사용하면 AppConfig에 있는 @Bean들을 스캔해서 스프링 빈으로 등록할 수 있다.
 @ComponentScan은 @Component가 붙은 모든 클래스를 스프링 빈으로 등록한다.

 excludeFilters 는 컴포넌트 스캔 대상에서 제외할 필터를 지정한다.
    ㄴ type = FilterType.ANNOTATION : 기본값, 애노테이션을 인식해서 동작한다.
    ㄴ classes = Configuration.class : Configuration 애노테이션이 붙은 클래스를 제외한다.
 */
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Configuration.class)
)
public class AutoAppConfig {


}
