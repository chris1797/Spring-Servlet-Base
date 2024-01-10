package com.base.servlet.web.frontcontroller.v1.controller;

import com.base.servlet.domain.member.MemberRepository;
import com.base.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.server.ServerCloneException;

public class MemberFormControllerV1 implements ControllerV1 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String viewPath = "/WEB-INF/views/test-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
