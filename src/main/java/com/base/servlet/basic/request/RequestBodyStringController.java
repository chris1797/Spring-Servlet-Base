package com.base.servlet.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    /*
        * HTTP 요청 메시지 바디의 내용을 직접 조회하는 방법
        * HTTP 메시지 바디의 데이터를 읽기 위해서는 InputStream 또는 Reader를 사용해야 한다.
        * 이것은 HTTP 요청 메시지를 직접 읽는 기능이므로 요청 파라미터를 조회하는 기능과 별도로 제공된다.
        *
        * HTTP 요청 메시지의 내용을 직접 조회하는 기능은 요청 파라미터를 조회하는 기능과 매우 유사하다.
        * 따라서 스프링 MVC는 이 둘을 편리하게 사용할 수 있도록 여러 메서드를 제공한다.
        * ex) @RequestParam, @ModelAttribute
     */
    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletInputStream inputStream = request.getInputStream();

        // Stream은 Byte 코드이므로 문자로 변환해주어야 한다. (인코딩 지정 필요)
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);

        response.getWriter().write("ok");
    }

    /*
        * InputStream(Reader) : HTTP 요청 메시지 바디의 내용을 직접 조회
        * 요청 파라미터와 마찬가지로 응답 파라미터에도 Writer를 사용할 수 있다.
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        // InputStream을 사용하면 byte 코드로 읽기 때문에 문자로 변환해주어야 한다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}", messageBody);
        responseWriter.write("ok");
    }

    // HttpEntity : HTTP header, body 정보를 편리하게 조회
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        // HttpEntity는 HTTP 요청, 응답의 헤더 정보 및 바디 정보를 편리하게 조회할 수 있는 기능을 제공
        // 요청 파라미터를 조회하는 기능과는 관계가 없다. (@RequestParam, @ModelAttribute)
        String messageBody = httpEntity.getBody();
        log.info("messageBody = {}", messageBody);

        // HttpEntity를 상속받는 것들이 RequestEntity, ResponseEntity

        // HttpEntity는 응답에도 사용할 수 있음.
        return new HttpEntity<>("ok");
    }
}
