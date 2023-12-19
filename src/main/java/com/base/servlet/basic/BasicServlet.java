package com.base.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Array;


@WebServlet(name = "basicServlet", urlPatterns = "/api")
public class BasicServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Start BasicServlet :::");
        System.out.println("request ::: " + request); // org.apache.catalina.connector.RequestFacade@464c9024
        System.out.println("response ::: " + response); // org.apache.catalina.connector.ResponseFacade@5820cacb

        String userName = request.getParameter("userName");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + userName);

        
    }
}
