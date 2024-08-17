package core.scan.filter;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        var ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        for (var name : ac.getBeanDefinitionNames()) {
            // beanA가 등록되어 있음을 확인할 수 있다. :: name = beanA
            System.out.println("name = " + name);
        }

        BeanA beanA = ac.getBean(BeanA.class);

        // BeanA는 include 대상이므로 스프링 빈에 등록된다.
        assertThat(beanA).isNotNull();

        // BeanB는 include 대상에서 제외되었으므로 스프링 빈에 등록되지 않는다.
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean(BeanB.class));
    }

    @Configuration
    @ComponentScan(
            // type = FilterType.ANNOTATION 기본값
            includeFilters = @ComponentScan.Filter(MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}
