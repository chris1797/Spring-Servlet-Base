package com.base.servlet.web.frontcontroller.v5;

import com.base.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.base.servlet.web.frontcontroller.v4.ControllerV4;
import com.base.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.base.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();

    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v3/members/save", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v3/members", new MemberFormControllerV3());
    }
}
