package com.base.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        System.out.println("===================== 전체 파라미터 =====================");

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " ::: " + request.getParameter(paramName)));


        System.out.println("===================== 단일 파라미터 =====================");
        String userName = request.getParameter("userName");
        String age = request.getParameter("age");

        System.out.println("userName = " + userName);
        System.out.println("age = " + age);

        System.out.println("===================== 이름이 같은 복수의 파라미터 조회 =====================");
        /*
        http://localhost:8080/request-param?userName=tom&age=20&userName=chris로 호출했을 때
         */
        List<String> userNames = List.of(request.getParameterValues("userName"));
//        String[] userNames = request.getParameterValues("userName");

        for (String name : userNames) {
            System.out.println("userName ::: " + name);
        }
        System.out.println("userNames :::" + userNames);

        response.getWriter().write("ok");
    }
}
