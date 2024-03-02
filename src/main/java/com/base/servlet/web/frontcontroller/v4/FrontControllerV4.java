package com.base.servlet.web.frontcontroller.v4;

import com.base.servlet.web.frontcontroller.MyView;
import com.base.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.base.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.base.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
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
@WebServlet(name = "frontControllerV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerV4 extends HttpServlet {

    private final Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerV4() {
        /**
         * URI를 key, URI에 따른 각 컨트롤러를 value로 저장한 뒤에
         * request URI에 따른 컨트롤러 인스턴스를 반환
         */
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // request에 담긴 데이터들을 paramMap으로 변환
        Map<String, String> paramMap = createParamMap(request);

        // model을 담을 수 있는 Map 생성해서 넘겨주기
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);

        // viewName을 받아 실제 view로 변환
        MyView view = viewResolver(viewName);

        // model에 있는 데이터를 모두 request에 담아서 view에 전달
        view.render(model, request, response);
    }


    /* ------------------------------ private ------------------------------ */
    /* ------------------------------ private ------------------------------ */
    /* ------------------------------ private ------------------------------ */
    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }


    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        // request에 담긴 데이터들을 추출하여 paramMap으로 변환
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }
}
