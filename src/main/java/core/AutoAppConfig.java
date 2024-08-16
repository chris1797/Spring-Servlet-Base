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
        /*
        탐색할 패키지의 시작 위치를 지정한다. 해당 패키지를 포함해서 하위 패키지를 모두 탐색한다.
        만약 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
        관례로는 설정 정보 클래스의 위치를 프로젝트 최상단에 둔다. 그럼 basePackages를 지정하지 않아도 된다.
        @SpringBootApplication 에 사실 @ComponentScan 이 포함되어 있다. 그래서 특별히 프로젝트 시작 위치를 지정하지 않아도 잘 동작한다.
         */
        basePackages = "core.member",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Configuration.class)
)
public class AutoAppConfig {


}
