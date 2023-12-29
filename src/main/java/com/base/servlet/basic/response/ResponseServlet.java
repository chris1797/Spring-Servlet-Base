package com.base.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);

        // 클라이언트 header content를 직접 입력하여 응답
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Custom-Header", "hello");

        setCookie(response);
        redirect(response);


        PrintWriter writer = response.getWriter();
        writer.println("하이요"); // Content-Type 에 ;charset=utf-8을 넣어 줬기 때문에 한글 가능

    }

    private static void redirect(HttpServletResponse response) throws IOException {
        // Status Code 302

        response.setStatus(HttpServletResponse.SC_FOUND);
        response.sendRedirect("/basic/form.html");
    }

    private static void setCookie(HttpServletResponse response) {
        /*
        Set-Cookie: custom-cookie=good; Max-Age=600;
        -> response.setHeader("Set-Cookie", "custom-cookie=good; Max-Age=600");
        아래 코드와 같음
         */
        Cookie cookie = new Cookie("custom-cookie", "good");
        cookie.setMaxAge(600); // 600초 동안 유효

        response.addCookie(cookie);
    }
}
