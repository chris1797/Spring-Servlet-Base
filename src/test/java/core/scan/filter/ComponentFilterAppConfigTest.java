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
            System.out.println("name = " + name);
        }

        BeanA beanA = ac.getBean(BeanA.class);
        assertThat(beanA).isNotNull();

        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean(BeanB.class));
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}
