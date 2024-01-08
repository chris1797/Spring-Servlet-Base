package com.base.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/test-form.jsp";

        // Controller에서 해당 View로 이동할 때 사용
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);

        // Servlet에서 jsp를 호출, 서버 내부에서 서로 호출하며 제어권을 넘기는 식
        dispatcher.forward(request, response);
    }
}
