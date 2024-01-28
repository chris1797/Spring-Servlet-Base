package com.base.servlet.web.frontcontroller.v3;

import com.base.servlet.web.frontcontroller.ModelView;
import com.base.servlet.web.frontcontroller.MyView;
import com.base.servlet.web.frontcontroller.v3.ControllerV3;
import com.base.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.base.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.rmi.server.ServerCloneException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@WebServlet(name = "frontControllerV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerV3 extends HttpServlet {

    private final Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerV3() {
        /**
         * URI를 key, URI에 따른 각 컨트롤러를 value로 저장한 뒤에
         * request URI에 따른 컨트롤러 인스턴스를 반환
         */
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // paramMap을 넘겨줘야 함
        Map<String, String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }


    /* ------------------------------ private ------------------------------ */
    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
