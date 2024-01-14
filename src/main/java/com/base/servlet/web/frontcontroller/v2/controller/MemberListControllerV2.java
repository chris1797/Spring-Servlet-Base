package com.base.servlet.web.frontcontroller.v2.controller;

import com.base.servlet.domain.member.Member;
import com.base.servlet.domain.member.MemberRepository;
import com.base.servlet.web.frontcontroller.MyView;
import com.base.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.server.ServerCloneException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServerCloneException, IOException, ServletException {

        List<Member> members = memberRepository.findAll();
        request.setAttribute("members", members);

        return new MyView("/WEB-INF/views/test-form.jsp");
    }
}
