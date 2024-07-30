package mvc.springmvc.basic.request;

import com.base.servlet.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        // @ModelAttribute HelloData helloData를 사용하면 요청 파라미터를 객체에 바인딩할 수 있다.
        /*
        @ModelAttribute는 생략해도 되지만, 생략하면 model.addAttribute(helloData)가 생략된 것으로 처리된다.

        따라서 model.addAttribute("helloData", helloData)가 내부적으로 호출되어 request에 helloData가 저장된다.
        이렇게 되면 뷰에서 helloData를 꺼내 쓸 수 있다.

        @ModelAttribute 동작 방식
            1. HelloData 객체를 생성한다.
            2. 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다. 그리고 해당 프로퍼티의 setter를 호출해서 값을 바인딩한다.
            3. 예) 요청 파라미터 이름이 username이면 setUsername() 메서드를 찾아서 호출하면서 값을 바인딩한다.
            4. 바인딩할 때 타입을 변환하는 과정이 필요하면 이 변환기를 적용한다.
            5. 따라서 HelloData 객체를 생성하고 setter를 호출하는 과정이 생략되기 때문에 HelloData 객체의 프로퍼티 이름과 요청 파라미터 이름이 같아야 한다.

        이렇게 동작하는 이유는 @ModelAttribute는 대상 객체를 생성하고, 요청 파라미터의 값을 바인딩해서 넣어주기 때문이다.
        */

        log.info("username={}, age={}", helloData.getUserName(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        // @ModelAttribute 생략 가능, 스프링은 @ModelAttribute 가 생략되어도 해당 객체를 생성해서 모델에 추가해준다.
        // @RequestParam은 주로 단순한 파라미터를 받을 때 사용하고, @ModelAttribute는 복잡한 객체를 바인딩 받을 때 주로 사용한다.
        log.info("username={}, age={}", helloData.getUserName(), helloData.getAge());

        return "ok";
    }
}
