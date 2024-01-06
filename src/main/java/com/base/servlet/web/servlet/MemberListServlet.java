package com.base.servlet.web.servlet;

import com.base.servlet.domain.member.Member;
import com.base.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();


        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        // html 성공폼 응답 생략
        System.out.println("members = " + members);
        response.getWriter().write("ok");
    }
}
