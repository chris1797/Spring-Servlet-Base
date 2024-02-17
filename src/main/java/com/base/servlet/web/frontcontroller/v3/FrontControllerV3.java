package com.base.servlet.web.frontcontroller.v3;

import com.base.servlet.web.frontcontroller.ModelView;
import com.base.servlet.web.frontcontroller.MyView;
import com.base.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
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

        // request에 담긴 데이터들을 paramMap으로 변환
        Map<String, String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName(); // view의 논리적 이름
        MyView view = viewResolver(viewName); // viewName을 받아 실제 view로 변환

        // model에 있는 데이터를 모두 request에 담아서 view에 전달
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
