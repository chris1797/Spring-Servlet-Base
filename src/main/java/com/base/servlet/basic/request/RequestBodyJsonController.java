package com.base.servlet.basic.request;

import com.base.servlet.basic.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUserName(), helloData.getAge());

        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody, HttpServletResponse response) throws Exception {
        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUserName(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        /**
         * @RequestBody: HTTP 메시지 컨버터가 HTTP 메시지 바디의 내용을 우리가 원하는 문자나 객체 등으로 변환해준다.
         * HTTP 메시지 컨버터는 문자 뿐만 아니라 JSON도 객체로 변환해주는데, 이때 HTTP 메시지 컨버터는 Jackson 라이브러리를 사용한다.
         * 따라서 Jackson 라이브러리가 클래스의 필드에 있는 @JsonProperty 등의 정보를 활용해서 JSON 데이터를 객체로 변환한다.
         *
         * application/json 인 경우 HTTP 메시지 컨버터가 동작하기 때문에 HTTP 요청 시 Content-Type이 application/json인지 꼭 확인하고, 그에 맞는 데이터를 전송해야 한다.
         */
        log.info("username={}, age={}", helloData.getUserName(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
        // Generic 타입을 지정하면 HttpEntity에서 제네릭 타입으로 바로 조회 가능
        HelloData helloData = httpEntity.getBody();
        log.info("username={}, age={}", helloData.getUserName(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {
        // 반환 타입이 HelloData이므로 HelloData 객체를 반환하면 JSON으로 변환되어 응답된다.
        log.info("username={}, age={}", helloData.getUserName(), helloData.getAge());
        return helloData;
    }
}
