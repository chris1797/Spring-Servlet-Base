package core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
        String uuid = java.util.UUID.randomUUID().toString();
        System.out.println("[" + uuid + "][" + requestURL + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "][" + requestURL + "] close:" + this);
    }
}
