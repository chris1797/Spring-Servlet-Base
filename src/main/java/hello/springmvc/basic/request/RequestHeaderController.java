package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie) {

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);

        // 가장 우선 순위가 높은 locale 정보를 받을 수 있다. LocaleResolver를 사용하면 더 정확한 locale 정보를 받을 수 있다.
        log.info("locale={}", locale);

        // MultiValueMap을 사용하면 하나의 헤더에 여러 값을 받을 수 있다.
        // Map과 유사하며 하나의 키에 여러 값을 받을 수 있다.
        log.info("headerMap={}", headerMap);

        headerMap.add("headerName", "headerValue1");
        headerMap.add("headerName", "headerValue2");

        // [headerValue1, headerValue2] 로 반환됨
        List<String> headerName = headerMap.get("headerName");

        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }
}
