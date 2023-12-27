package com.base.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        printStartLine(request);
        printCookies(request);

        printLocaleInfo(request);
    }

    private static void printStartLine(HttpServletRequest request) {
        System.out.println(" ======== REQUEST START ======== ");

        System.out.println("request.getMethod() ::: " + request.getMethod()); // GET
        System.out.println("request.getProtocol() ::: " + request.getProtocol()); // HTTP/1.1
        System.out.println("request.getScheme() ::: " + request.getScheme()); // http

        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + " ::: " + headerName));
    }

    private static void printCookies(HttpServletRequest request) {
        System.out.println(" ======== Cookies START ======== ");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + " ::: " + cookie.getValue());
            }
        }
    }

    private static void printLocaleInfo(HttpServletRequest request) {
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("Locale ::: " + locale));
        System.out.println("request.getLocale() ::: " + request.getLocale());
    }
}
