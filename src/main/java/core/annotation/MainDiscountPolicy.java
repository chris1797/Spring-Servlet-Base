package core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
    /**
     * <p>Qualifier를 사용하면 동일한 타입의 빈이 여러 개 존재할 때, 어떤 빈을 주입할지 선택할 수 있다.</p>
     * 해당 애노테이션을 사용하면, @Autowired 시, @Qualifier("mainDiscountPolicy")를 사용하여 주입할 빈을 선택할 수 있다.
     */
}
