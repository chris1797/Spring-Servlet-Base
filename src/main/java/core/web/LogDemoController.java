package core.web;

import core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogDemoController {

    /**
     * <h3>ObjectProvider</h3>
     * <p>ObjectProvider 는 지정한 빈을 컨테이너에서 대신 찾아주는 DL(dependency lookup) 빈이다.</p>
     * <p>MyLogger 는 request scope 이므로 HTTP 요청이 들어올 때마다 새로운 MyLogger 가 생성되어야 하는데,</p>
     * <p>ObjectProvider 없이 MyLogger가 생성되는 시점에 주입받으면, MyLogger 는 싱글톤 빈이므로 주입받은 시점의 MyLogger 를 계속 사용하게 된다.</p>
     * <p>ObjectProvider 를 사용하면 개발자가 원하는, getObject() 를 호출하는 시점에 컨테이너에서 MyLogger 를 찾아서 반환한다.</p>
     *
     *
     */
    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final LogDemoService logDemoService;

    @RequestMapping("log-demo")
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }

}
