package com.base.servlet.web.frontcontroller.v1;

import com.base.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.rmi.server.ServerCloneException;
import java.util.HashMap;
import java.util.Map;

// "/front-controller/v1/" 로 들어오는 요청은 전부 받도록 설정
@Slf4j
@WebServlet(name = "frontControllerV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerV1 extends HttpServlet {

    private final Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    public FrontControllerV1() {
        /**
         * URI를 key, URI에 따른 각 컨트롤러를 value로 저장한 뒤에
         * request URI에 따른 컨트롤러 인스턴스를 반환
         */
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
//        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
//        controllerV1Map.put("/front-controller/v1/members/list", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.warn("FrontControllerV1 Start :::::");

        /**
         * 클라이언트로부터 요청된 URI에 따른 컨트롤러 인스턴스를 controllerV1Map에서 꺼냄
         */
        String requestURI = req.getRequestURI();

        ControllerV1 controller = controllerV1Map.get(requestURI);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            controller.process(req, resp);
        } catch (ServerCloneException e) {
            throw new RuntimeException(e);
        }
    }
}
