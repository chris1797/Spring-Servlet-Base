package com.base.servlet.web.frontcontroller.v2;

import com.base.servlet.web.frontcontroller.MyView;
import com.base.servlet.web.frontcontroller.v1.ControllerV1;
import com.base.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.base.servlet.web.frontcontroller.v2.controller.MemberformControllerV2;
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
@WebServlet(name = "frontControllerV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerV2 extends HttpServlet {

    private final Map<String, ControllerV2> controllerV1Map = new HashMap<>();

    public FrontControllerV2() {
        /**
         * URI를 key, URI에 따른 각 컨트롤러를 value로 저장한 뒤에
         * request URI에 따른 컨트롤러 인스턴스를 반환
         */
        controllerV1Map.put("/front-controller/v2/members/new-form", new MemberformControllerV2());
//        controllerV1Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
//        controllerV1Map.put("/front-controller/v2/members/list", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV2 controller = controllerV1Map.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            MyView view = controller.process(request, response);
            view.render(request, response);
        } catch (ServerCloneException e) {
            throw new RuntimeException(e);
        }

    }
}
