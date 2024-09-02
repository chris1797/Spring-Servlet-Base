package core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * <h3>Request Scope bean</h3>
 * <p>LogDemoController, LogDemoService 에서 각각 다른 MyLogger 가 생성되어도 같은 HTTP 요청이면 같은 MyLogger 를 참조하도록 하기 위해 request scope 로 설정했다.</p>
 * MyLogger 는 HTTP 요청 당 하나씩 생성되고, HTTP 요청이 끝나는 시점에 소멸한다.
 */
@Setter
@Component
@Scope(value = "request")
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
