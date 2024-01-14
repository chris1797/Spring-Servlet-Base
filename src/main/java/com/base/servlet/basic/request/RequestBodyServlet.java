package com.base.servlet.basic.request;

import com.base.servlet.basic.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@WebServlet(name = "requestBodyServlet", urlPatterns = "/api/body")
public class RequestBodyServlet extends HttpServlet {

    /**
     * JSON 데이터를 처리해주는 jackson 라이브러리, <p>
     * JSON 결과를 파싱해서 자바 객체로 변환하기 위해 ObjectMapper를 사용
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // messageBody 내용을 byte 코드로 출력
        ServletInputStream inputStream = request.getInputStream();
        log.warn("inputStream ::: " + inputStream); // org.apache.catalina.connector.CoyoteInputStream@3626bd73

        // byte를 String으로 변환할때는 항상 인코딩할 형식 기재
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.warn("messageBody = " + messageBody);


        HelloData data = objectMapper.readValue(messageBody, HelloData.class);
        log.warn("helloData.userName = " + data.getUserName());
        log.warn("helloData.age = " + data.getAge());

        response.getWriter().write("ok");
     }
}
