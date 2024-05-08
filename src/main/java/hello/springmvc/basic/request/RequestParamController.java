package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    @RequestMapping("/request-param-v2")
    public void requestParamV2(@RequestParam("username") String username, // @RequestParam("username")을 생략하면 파라미터 이름으로 매핑
                               @RequestParam("age") int age,
                               HttpServletResponse response) throws IOException {
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @RequestMapping("/request-param-v3")
    public void requestParamV3(@RequestParam String memberName,
                               @RequestParam int memberAge,
                               HttpServletResponse response) throws IOException {
        log.info("username={}, age={}", memberName, memberAge);

        response.getWriter().write("ok");
    }

    @RequestMapping("/request-param-v4")// @RequestParam 생략 가능
    public void requestParamV4(String username, int age, HttpServletResponse response) throws IOException {
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @RequestMapping("/request-param-required")
    public void requestParamRequired(@RequestParam(required = true) String username,
                                     @RequestParam(required = false) Integer age,
                                     HttpServletResponse response) throws IOException {

        // required = true로 설정하면 해당 파라미터가 반드시 있어야 한다.
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @RequestMapping("/request-param-default")
    public void requestParamDefault(@RequestParam(defaultValue = "guest") String username,
                                    @RequestParam(defaultValue = "-1") int age,
                                    HttpServletResponse response) throws IOException {

        // defaultValue를 설정하면 해당 파라미터가 없을 때 기본 값을 설정할 수 있다.
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        // @RequestParam Map<String, Object> paramMap를 사용하면 모든 요청 파라미터를 Map으로 받을 수 있다.
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

}
