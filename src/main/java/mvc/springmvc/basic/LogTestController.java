package mvc.springmvc.basic;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LogTestController {

    private final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        // println을 사용하면 로그를 남길 때마다 문자열 더하기 연산이 발생한다.
        // 또한 레발에 상관없이 모두 출력된다.
        System.out.println("name = " + name);

        log.trace("trace log={}", name);
        log.debug("debug log={}", name); // 개발 레벨에서는 debug 레벨까지 출력
        log.info("info log={}", name); // 운영 레벨에서는 보통 info 레벨까지만 출력
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
