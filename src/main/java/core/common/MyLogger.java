package core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * <h3>Request Scope bean</h3>
 * <p>LogDemoController, LogDemoService 에서 각각 다른 MyLogger 가 생성되어도 같은 HTTP 요청이면 같은 MyLogger 를 참조하도록 하기 위해 request scope 로 설정했다.</p>
 * <p>MyLogger 는 HTTP 요청 당 하나씩 생성되고, HTTP 요청이 끝나는 시점에 소멸한다.</p>
 *
 * <br/>
 *
 * <h3>ProxyMode</h3>
 * <p>proxyMode = ScopedProxyMode.TARGET_CLASS 를 설정하면 CGLIB 라이브러리를 사용해서 이 MyLogger를 상속받는 가짜 프록시 객체를 만들어둔다.</p>
 * <p>이 가짜 프록시 객체는 실제 요청이 오면 그때 내부에서 실제 빈을 요청하는 방식으로 동작한다.</p>
 * <p>이렇게 되면 실제 request scope 빈이 생성되는 시점을 지연시킬 수 있다.</p>
 * <p>선언이 class에 있다면 TARGET_CLASS, interface에 있다면 INTERFACES 로 설정한다.</p>
 */
@Setter
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;


    public void log(String message) {
        System.out.println("[" + uuid + "][" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        this.uuid = java.util.UUID.randomUUID().toString();
        System.out.println("[" + uuid + "][" + requestURL + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "][" + requestURL + "] close: " + this);
    }
}
