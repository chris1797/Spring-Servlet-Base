package com.base.servlet.web.frontcontroller.v2.controller;

import com.base.servlet.web.frontcontroller.MyView;
import com.base.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.server.ServerCloneException;

public class MemberformControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServerCloneException, IOException, ServletException {
        return new MyView("/WEB-INF/views/test-form.jsp");
    }

}
